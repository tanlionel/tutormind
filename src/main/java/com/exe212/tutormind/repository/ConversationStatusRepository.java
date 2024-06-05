package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.ConversationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationStatusRepository extends JpaRepository<ConversationStatus, Integer> {
}
