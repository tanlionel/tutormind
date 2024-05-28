package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    public Page<Profile> findAll(Pageable pageable);
    public Profile findByUserId(Integer userId);

    @Query("SELECT DISTINCT p " +
            "FROM Profile p JOIN Major m ON p.user.id = m.user.id " +
            "WHERE " +
            "(m.subject.id IN :subjectIdList OR :subjectIdList IS NULL) " +
            "OR ( NOT (:search IS NULL OR :search = '') " +
            "AND (p.user.fullName LIKE '%' || :search || '%' " +
            "OR m.subject.name = :search) )")
    public Page<Profile> getProfilePagination(Pageable pageable,
                                        @Param("search") String search,
                                        @Param("subjectIdList") List<Integer> subjectIdList);
}
