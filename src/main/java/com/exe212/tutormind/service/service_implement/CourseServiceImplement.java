package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDTO;
import com.exe212.tutormind.repository.CourseRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseServiceImplement implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
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
    public Course getCourseByCourseId(Integer courseId) throws NotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        return course;
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
        courseDB.setDescription(course.getDescription());
        courseDB.setSimpleDescription(course.getSimpleDescription());
        return courseRepository.save(courseDB);
    }
}
