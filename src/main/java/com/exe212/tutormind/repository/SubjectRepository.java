package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    public List<Subject> findAllByNameContaining(String name);

}
