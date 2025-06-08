package com.example.pasir_lubera_jakub.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Dodaj konstruktor bez argumentów
import lombok.AllArgsConstructor; // Dodaj konstruktor ze wszystkimi argumentami
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupTransactionDTO {

    private Long groupId;
    private Double amount;
    private String type;
    private String title;
    private List<Long> selectedUserIds;

}