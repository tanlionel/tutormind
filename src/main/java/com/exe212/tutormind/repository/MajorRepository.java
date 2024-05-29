package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Integer> {
    @Query("SELECT m " +
            "FROM Major m " +
            "WHERE (m.subject.id IN :subjectIdList OR :subjectIdList = null) OR " +
            "m.user.fullName LIKE %:search% ")
    public Page<Major> findMajorPageable(Pageable pageable,
                                         @Param("search") String search,
                                         @Param("subjectIdList") List<Integer> subjectIdList);
}
