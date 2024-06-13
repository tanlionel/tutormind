package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.ActivityLog;
import com.exe212.tutormind.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public Page<Transaction> findAllByOwnerIdOrReceiverId(Pageable pageable,
                                                          Integer ownerId,
                                                          Integer receiverId);

}
