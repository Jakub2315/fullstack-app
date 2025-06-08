package com.example.pasir_lubera_jakub.dto;

import com.example.pasir_lubera_jakub.model.User; // Zakładam, że User to klasa z Twojego projektu
import jakarta.persistence.*; // Ważne, aby używać Jakarta Persistence zamiast javax.persistence dla nowszych wersji Springa/Javy EE
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor; // Dodaj AllArgsConstructor, jeśli chcesz mieć konstruktor z wszystkimi polami

import java.time.LocalDateTime;

@Entity
@Table(name = "groups_dto") // Dobra praktyka to jawne określenie nazwy tabeli
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Dodany konstruktor ze wszystkimi argumentami
public class GroupDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255) // Dodano długość kolumny dla Stringa
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // Dodano FetchType.LAZY dla optymalizacji, aby owner nie był ładowany od razu
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false) // referencedColumnName dla jasności
    private User owner; // Właściciel grupy

    @Column(name = "created_at", nullable = false, updatable = false) // updatable = false, aby data nie była zmieniana po utworzeniu
    private LocalDateTime createdAt = LocalDateTime.now(); // Domyślna wartość ustawiana przy tworzeniu obiektu
}