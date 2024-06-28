package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser,Integer> {
    CourseUser findCourseUserByCourse_IdAndStudent_Id(Integer courseId,Integer studentId);
}
