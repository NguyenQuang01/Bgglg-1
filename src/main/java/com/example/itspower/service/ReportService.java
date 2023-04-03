package com.example.itspower.service;

import com.example.itspower.request.ReportRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    Object reportDto(String reportDate, int groupId);

    ResponseEntity<Object> save(ReportRequest request, int groupId);

    ResponseEntity<Object> update(ReportRequest request, int groupId);
    void deleteRestIdsAndReportId(Integer reportId,List<Integer> restIds);
}
