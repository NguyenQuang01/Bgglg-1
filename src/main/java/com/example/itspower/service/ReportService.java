package com.example.itspower.service;

import com.example.itspower.request.ReportRequest;
import com.example.itspower.response.ReportResponse;

public interface ReportService {
    ReportResponse reportDto(String reportDate, int groupId);

    ReportResponse save(ReportRequest request, int groupId);

    ReportResponse update(ReportRequest request, int groupId);
}
