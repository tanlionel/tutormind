package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.CourseUser;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDTO;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<Course> getCourseList(Integer pageNo, Integer pageSize, String search);
    Page<Course> getCourseListByTutor(Integer pageNo,Integer pageSize,String search,Integer tutorId);
    CourseDetailDTO getCourseByCourseId(Integer courseId) throws NotFoundException;
    Course createCourse(CourseDTO course);
    Course updateCourse(Integer courseId,CourseDTO course) throws NotFoundException;

    CourseUser enrollCourse(Integer courseId, Integer userId) throws NotFoundException;

    CourseUser getEnrollCourse(Integer courseId, Integer userId);
}
