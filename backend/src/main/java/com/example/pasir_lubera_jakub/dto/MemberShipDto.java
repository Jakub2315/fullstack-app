package com.example.pasir_lubera_jakub.dto;

import jakarta.validation.constraints.NotBlank; // Bardziej odpowiednie dla Stringów niż NotNull
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Dodaj konstruktor bez argumentów
import lombok.AllArgsConstructor; // Dodaj konstruktor ze wszystkimi argumentami

/**
 * Klasa DTO reprezentująca dane członkostwa (np. do grupy).
 * Używana do walidacji danych wejściowych.
 */
@Getter
@Setter
@NoArgsConstructor // Dodaj konstruktor bez argumentów (wymagany przez niektóre frameworki, np. Spring)
@AllArgsConstructor // Dodaj konstruktor ze wszystkimi argumentami (ułatwia tworzenie instancji)
public class MemberShipDto {

    // Użyj @NotBlank zamiast @NotNull dla Stringów, ponieważ sprawdza również, czy string nie jest pusty lub składa się z samych białych znaków.
    @NotBlank(message = "Email użytkownika nie może być pusty")
    private String userEmail;

    // @NotNull jest odpowiednie dla typów obiektowych, takich jak Long.
    @NotNull(message = "ID grupy nie może być puste")
    private Long groupId;
}