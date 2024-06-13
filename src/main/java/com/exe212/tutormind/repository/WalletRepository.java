package com.exe212.tutormind.repository;

import com.exe212.tutormind.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    public Wallet findByUserId(Integer userId);
}
