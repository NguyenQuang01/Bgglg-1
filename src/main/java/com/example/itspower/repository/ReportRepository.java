package com.example.itspower.repository;

import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportRepository {
    @Autowired
    private ReportJpaRepository reportJpaRepository;

    public ReportDto reportDto(String reportDate) {
        return reportJpaRepository.findByReport(reportDate);
    }
}
