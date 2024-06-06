package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.Lessons;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import com.exe212.tutormind.repository.CourseRepository;
import com.exe212.tutormind.repository.LessonsRepository;
import com.exe212.tutormind.service.service_interface.LessonService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImplement implements LessonService {
    @Autowired
    LessonsRepository lessonsRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Lessons getLessonById(Integer lessonId) throws BadRequestException {
        Lessons lessons = lessonsRepository.findById(lessonId).orElseThrow(BadRequestException::new);
        return lessons;
    }

    @Override
    public CourseDetailDTO createLesson(Integer courseId, List<Lessons> lessons) throws NotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        for(Lessons tmp: lessons){
            tmp.setId(0);
            tmp.setCourse(course);
            lessonsRepository.save(tmp);
        }
        List<Lessons> lessonsList = lessonsRepository.findAllByCourse_Id(courseId);
        CourseDetailDTO courseDetailDTO = CourseDetailDTO.builder()
                .id(course.getId())
                .price(course.getPrice())
                .tutor(course.getTutor())
                .title(course.getTitle())
                .simpleDescription(course.getSimpleDescription())
                .description(course.getDescription())
                .lessonsList(lessonsList)
                .build();
        return courseDetailDTO;
    }

    @Override
    public CourseDetailDTO updateLesson(CourseDetailDTO courseDetailDTO) throws NotFoundException, BadRequestException {
        Course course = courseRepository.findById(courseDetailDTO.getId()).orElseThrow(NotFoundException::new);
        for(Lessons tmp: courseDetailDTO.getLessonsList()){
            Lessons lessonDB = lessonsRepository.findById(tmp.getId()).orElseThrow(BadRequestException::new);
            lessonDB.setDescription(tmp.getDescription());
            lessonDB.setTitle(tmp.getTitle());
            lessonsRepository.save(lessonDB);
        }
        List<Lessons> lessonsList = lessonsRepository.findAllByCourse_Id(courseDetailDTO.getId());
        CourseDetailDTO courseDetailDTODB = CourseDetailDTO.builder()
                .id(course.getId())
                .price(course.getPrice())
                .tutor(course.getTutor())
                .title(course.getTitle())
                .simpleDescription(course.getSimpleDescription())
                .description(course.getDescription())
                .lessonsList(lessonsList)
                .build();
        return courseDetailDTODB;
    }
}
