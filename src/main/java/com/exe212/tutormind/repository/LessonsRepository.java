package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonsRepository extends JpaRepository<Lessons,Integer> {
    List<Lessons> findAllByCourse_Id(Integer courseId);
}
