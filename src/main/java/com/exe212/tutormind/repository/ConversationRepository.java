package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Conversation;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    Page<Conversation> findAllByTeacherFullNameContainingAndTeacherIdOrUserId(Pageable pageable,
                                                                              String teacherFullName,
                                                                              Integer teacherId,
                                                                              Integer userId);
    Page<Conversation> findAllByTeacherFullNameContaining(Pageable pageable,
                                                                              String teacherFullName);
    List<Conversation> findAllByTeacherEmailAndConversationStatusId(String teacherEmail,
                                                                    Integer conversationStatusId);
    @Query("SELECT c FROM Conversation c " +
            "WHERE FUNCTION('MONTH', c.createdDate) = :month")
    public List<Conversation> findConversation(@Param("month") int month);

    @Query("SELECT c FROM Conversation c " +
            "WHERE c.createdDate BETWEEN :startDate AND :endDate")
    public List<Conversation> findConversationByRange(LocalDateTime startDate,
                                                      LocalDateTime endDate);

    @Query("SELECT COUNT(c.id) FROM Conversation c " +
            "WHERE c.conversationStatus.id = :status")
    public Long countAllConversationByStatus(Integer status);
}
