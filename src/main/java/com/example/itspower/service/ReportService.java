package com.example.itspower.service;

import com.example.itspower.request.ReportRequest;
import com.example.itspower.response.ReportResponse;

public interface ReportService {
    ReportResponse reportDto(String reportDate);

    ReportResponse save(ReportRequest request);
}
