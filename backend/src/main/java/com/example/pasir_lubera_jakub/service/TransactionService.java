package com.example.pasir_lubera_jakub.service;

import com.example.pasir_lubera_jakub.dto.TransactionDTO;
import com.example.pasir_lubera_jakub.dto.BalanceDto;
import com.example.pasir_lubera_jakub.model.Transaction;
import com.example.pasir_lubera_jakub.model.TransactionType;
import com.example.pasir_lubera_jakub.model.User;
import com.example.pasir_lubera_jakub.repository.TransactionRepository;
import com.example.pasir_lubera_jakub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    // Pobierz aktualnie zalogowanego użytkownika
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono zalogowanego użytkownika"));
    }

    // Zwraca wszystkie transakcje zalogowanego użytkownika
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAllByUser(getCurrentUser());
    }

    // Zwraca transakcję po ID (bez sprawdzania właściciela)
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono transakcji o ID " + id));
    }

    // Oblicz bilans użytkownika z opcjonalnym filtrem po czasie
    public BalanceDto getUserBalance(User user, Float days) {
        List<Transaction> transactions;

        if (days != null) {
            long secondsToSubtract = (long) (days * 24 * 60 * 60);
            LocalDateTime fromDate = LocalDateTime.now().minusSeconds(secondsToSubtract);
            transactions = transactionRepository.findByUserAndTimestampAfter(user, fromDate);
        } else {
            transactions = transactionRepository.findByUser(user);
        }

        double income = transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(t -> Math.abs(t.getAmount()))
                .sum();

        return new BalanceDto(income, expense, income - expense);
    }

    // Aktualizuj istniejącą transakcję
    public Transaction updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono transakcji o ID " + id));

        if (!transaction.getUser().getEmail().equals(getCurrentUser().getEmail())) {
            throw new SecurityException("Brak dostępu do edycji tej transakcji");
        }

        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(TransactionType.valueOf(String.valueOf(transactionDTO.getType())));
        transaction.setTags(transactionDTO.getTags());
        transaction.setNotes(transactionDTO.getNotes());
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    // Utwórz nową transakcję
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(TransactionType.valueOf(String.valueOf(transactionDTO.getType())));
        transaction.setTags(transactionDTO.getTags());
        transaction.setNotes(transactionDTO.getNotes());
        transaction.setUser(getCurrentUser());
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    // Usuń transakcję
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Nie znaleziono transakcji o ID " + id);
        }
        transactionRepository.deleteById(id);
    }
}
