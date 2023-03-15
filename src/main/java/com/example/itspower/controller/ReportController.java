package com.example.itspower.controller;

import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<Object> report(@RequestParam("reportDate") String reportDate) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.reportDto(reportDate));
    }
}
