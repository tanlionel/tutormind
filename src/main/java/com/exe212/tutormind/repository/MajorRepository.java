package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    public List<Major> findAllByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Major m WHERE m.user.id = :userId")
    public int deleteAllByUserId(@Param("userId") Integer userId);

}
