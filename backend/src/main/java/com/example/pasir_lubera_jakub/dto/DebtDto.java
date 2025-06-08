package com.example.pasir_lubera_jakub.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtDto {

    private Long debtorId;
    private Long creditorId;
    private Long groupId;
    private Double amount;
    private String title;
}