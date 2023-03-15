package com.example.itspower.service.impl;

import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public ReportDto reportDto(String reportDate) {
        ReportDto reportDto = reportRepository.reportDto(reportDate);
        return reportDto;
    }
}
