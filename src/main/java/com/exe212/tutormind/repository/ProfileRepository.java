package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    public Page<Profile> findAll(Pageable pageable);

}
