package com.example.itspower.controller;

import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/report/save")
    public ResponseEntity<Object> save(@Validated @RequestBody ReportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.add(request));
    }

    @GetMapping("/report/groupsName")
    public ResponseEntity<Object> getListUserGroup() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getListGroup());
    }
}
