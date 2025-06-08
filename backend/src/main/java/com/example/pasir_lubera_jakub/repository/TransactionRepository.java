package com.example.pasir_lubera_jakub.repository;

import com.example.pasir_lubera_jakub.model.Transaction;
import com.example.pasir_lubera_jakub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);
    List<Transaction> findByUser(User user);
    List<Transaction> findByUserAndTimestampAfter(User user, LocalDateTime timestamp);

}
