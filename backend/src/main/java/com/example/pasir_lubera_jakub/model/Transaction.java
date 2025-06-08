package com.example.pasir_lubera_jakub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Setter
    private String tags;
    @Setter
    private double amount;

    @Setter
    private String notes;

    @Setter
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Konstruktor z parametrami
    public Transaction(TransactionType type, double amount, String notes, String tags, User user,LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.notes = notes;
        this.tags = tags;
        this.user = user;
        this.timestamp = timestamp;
    }

    public Transaction(Double amount, TransactionType transactionType, String spłataDługu, @Email(message = "Podaj poprawny adres e-mail") @NotBlank(message = "Email jest wymagany") String s, User creditor) {
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return notes;
    }

    public String getTags() {
        return tags;
    }

}