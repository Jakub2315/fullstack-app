package com.example.pasir_lubera_jakub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDTO {

    private Long id;
    private Long userId;
    private Long groupId;
    private String userEmail;

}