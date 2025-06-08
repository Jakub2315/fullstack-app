package com.example.pasir_lubera_jakub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users") // Ustalmy nazwę tabeli - unikamy np. "user", które jest słowem zastrzeżonym w niektórych DB
public class User {

    @Id // pole 'id' to klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // wartość id będzie generowana automatycznie przez bazę
    private Long id;

    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    private String username;

    @Email(message = "Podaj poprawny adres e-mail")
    @NotBlank(message = "Email jest wymagany")
    private String email;

    @NotBlank(message = "Hasło nie może być puste")
    private String password;

    private String currency = "PLN"; // domyślna waluta - do wstępnych ustawień konta

}