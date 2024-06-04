package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    Page<Conversation> findAllByTeacherFullNameContaining(Pageable pageable, String teacherFullName);
}
