package com.example.itspower.service;

import com.example.itspower.request.ReportRequest;
import org.springframework.http.ResponseEntity;

public interface ReportService {
    Object reportDto(String reportDate, int groupId);

    ResponseEntity<Object> save(ReportRequest request, int groupId);

    ResponseEntity<Object> update(ReportRequest request, int groupId);
}
