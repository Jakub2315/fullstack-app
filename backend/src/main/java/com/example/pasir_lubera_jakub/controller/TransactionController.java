package com.example.pasir_lubera_jakub.controller;

import com.example.pasir_lubera_jakub.model.Transaction;
import com.example.pasir_lubera_jakub.dto.TransactionDTO;
import com.example.pasir_lubera_jakub.repository.TransactionRepository;
import com.example.pasir_lubera_jakub.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
            this.transactionService = transactionService;
    }

    // 1. Pobieranie wszystkich transakcji (GET)
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction created = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    // 2. Pobieranie pojedynczej transakcji po ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // 3. Aktualizacja istniejącej transakcji (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}

