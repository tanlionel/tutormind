package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findUserByUsername(String username);
    Page<User> findUserByRoleId(Pageable pageable,
                                String search,
                                Integer roleId);
    Page<User> findByRoleNameContainingAndFullNameContaining(String roleName, String search, Pageable pageable);

    public Integer countAllUserByRoleId(Integer roleId);
}
