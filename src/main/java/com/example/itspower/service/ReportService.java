package com.example.itspower.service;

import com.example.itspower.request.ReportRequest;

public interface ReportService {
    Object reportDto(String reportDate, int groupId);

    Object save(ReportRequest request, int groupId);

    Object update(ReportRequest request, int groupId);
}
