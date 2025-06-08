package com.example.pasir_lubera_jakub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Dodaj NoArgsConstructor dla kompletności
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDTO {

    private Long id;
    private String name;
    private Long ownerId;
}
