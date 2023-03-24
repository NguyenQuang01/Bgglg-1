package com.example.itspower.controller;

import com.example.itspower.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    @CrossOrigin
    public Object report(@RequestParam("reportDate") String reportDate, @RequestParam("groupId") int groupId) {
        return reportService.reportDto(reportDate, groupId);
    }

    @PostMapping("/report/save")
    @CrossOrigin
    public ResponseEntity<Object> save(@RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) {
        return reportService.save(reportRequest, groupId);
    }

    @PostMapping("/report/update")
    @CrossOrigin
    public ResponseEntity<Object> update(@RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) {
        return reportService.update(reportRequest, groupId);
    }
}
