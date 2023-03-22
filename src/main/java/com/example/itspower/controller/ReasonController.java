package com.example.itspower.controller;

import com.example.itspower.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReasonController {
    @Autowired
    private ReasonService reasonService;

    @GetMapping("/reason")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reasonService.getAll());
    }
}
