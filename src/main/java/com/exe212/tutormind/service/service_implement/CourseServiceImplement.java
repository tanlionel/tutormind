package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.CourseUser;
import com.exe212.tutormind.entity.Lessons;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDTO;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import com.exe212.tutormind.repository.CourseRepository;
import com.exe212.tutormind.repository.CourseUserRepository;
import com.exe212.tutormind.repository.LessonsRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.CourseService;
import com.exe212.tutormind.service.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplement implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LessonsRepository lessonsRepository;
    @Autowired
    CourseUserRepository courseUserRepository;
    @Autowired
    UserService userService;
    @Override
    public Page<Course> getCourseList(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo,pageSize).withSort(Sort.by("id").descending());
        Page<Course> coursePage = courseRepository.getCoursesByTitleContaining(search,pageable);
        return coursePage;
    }

    @Override
    public Page<Course> getCourseListByTutor(Integer pageNo, Integer pageSize, String search, Integer tutorId) {
        Pageable pageable = PageRequest.of(pageNo,pageSize).withSort(Sort.by("id").descending());
        Optional<User> tutor = userRepository.findById(tutorId);
        Page<Course> coursePage = courseRepository.getCoursesByTitleContainingAndTutor(search,tutor.get(),pageable);
        return coursePage;
    }

    @Override
    public CourseDetailDTO getCourseByCourseId(Integer courseId) throws NotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        List<Lessons> lessons = lessonsRepository.findAllByCourse_Id(courseId);

        User loginUser = userService.getLoginUser();

        boolean isEnroll = courseUserRepository.findCourseUserByCourse_IdAndStudent_Id(courseId, loginUser.getId()) != null;

        CourseDetailDTO courseDetailDTO = CourseDetailDTO.builder()
                .id(course.getId())
                .price(course.getPrice())
                .tutor(course.getTutor())
                .title(course.getTitle())
                .simpleDescription(course.getSimpleDescription())
                .description(course.getDescription())
                .lessonsList(lessons)
                .isEnroll(isEnroll)
                .build();

        return courseDetailDTO;
    }

    @Override
    public Course createCourse(CourseDTO course) {
        Optional<User> tutor = userRepository.findById(course.getTutorId());
        Course saveCourse = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .simpleDescription(course.getSimpleDescription())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .isActive(true)
                .image(course.getImage())
                .tutor(tutor.get())
                .price(course.getPrice())
                .build();
        return courseRepository.save(saveCourse);
    }

    @Override
    public Course updateCourse(Integer courseId, CourseDTO course) throws NotFoundException {
        Course courseDB = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        courseDB.setUpdatedDate(LocalDateTime.now());
        courseDB.setPrice(course.getPrice());
        courseDB.setTitle(course.getTitle());
        if (!course.getImage().isEmpty()) courseDB.setImage(course.getImage());
        courseDB.setDescription(course.getDescription());
        courseDB.setSimpleDescription(course.getSimpleDescription());
        return courseRepository.save(courseDB);
    }

    @Override
    public CourseUser enrollCourse(Integer courseId, Integer userId) throws NotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        CourseUser courseUser = CourseUser.builder()
                .student(user)
                .course(course)
                .build();

        return courseUserRepository.saveAndFlush(courseUser);
    }

    @Override
    public CourseUser getEnrollCourse(Integer courseId, Integer userId) {
        CourseUser courseUser = courseUserRepository.findCourseUserByCourse_IdAndStudent_Id(courseId,userId);
        return courseUser;
    }
}
