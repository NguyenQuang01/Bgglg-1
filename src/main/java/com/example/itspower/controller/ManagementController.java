package com.example.itspower.controller;

import com.example.itspower.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/admin")
public class ManagementController {
    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping("/root")
    public ResponseEntity<Object> getRoot(@RequestParam("date") String date) throws ParseException {
        return ResponseEntity.ok(managementService.getRoot(date));
    }
}
