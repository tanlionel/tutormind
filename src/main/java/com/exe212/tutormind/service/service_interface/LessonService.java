package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.Lessons;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface LessonService {
    Lessons getLessonById(Integer lessonId) throws BadRequestException;
    CourseDetailDTO createLesson(Integer courseId, List<Lessons> lessons) throws NotFoundException;
    CourseDetailDTO updateLesson(Integer courseId, List<Lessons> lessons) throws NotFoundException, BadRequestException;
}
