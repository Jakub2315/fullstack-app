package com.example.pasir_lubera_jakub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public String info() {
        return "Witaj w aplikacji budżetowej stworzonej ze Spring\n" +
                "Boot!";
    }
}
