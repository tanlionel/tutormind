package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> getCoursesByTitleContaining(String search, Pageable pageable);
    Page<Course> getCoursesByTitleContainingAndTutor(String search, User tutor, Pageable pageable);

    @Query("SELECT COUNT(c.id) FROM Course c " +
            "WHERE FUNCTION('MONTH', c.createdDate) = :month")
    public Long countCourseByMonth(@Param("month") int month);
}
