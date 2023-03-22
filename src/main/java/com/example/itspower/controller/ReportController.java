package com.example.itspower.controller;

import com.example.itspower.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<Object> report(@RequestParam("reportDate") String reportDate, @RequestParam("groupId") int groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.reportDto(reportDate, groupId));
    }

    @PostMapping("/report/save")
    public ResponseEntity<Object> save(@RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.save(reportRequest, groupId));
    }

    @PostMapping("/report/update")
    public ResponseEntity<Object> update(@RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.update(reportRequest, groupId));
    }
}
