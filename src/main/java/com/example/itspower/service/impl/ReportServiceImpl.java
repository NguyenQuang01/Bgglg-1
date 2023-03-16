package com.example.itspower.service.impl;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.repository.RestRepository;
import com.example.itspower.repository.TransferRepository;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private RestRepository restRepository;
    @Autowired
    private TransferRepository transferRepository;

    public ReportResponse reportDto(String reportDate) {
        try {
            ReportDto reportDto = reportRepository.reportDto(reportDate);
            List<RestDto> restDtos = restRepository.getRests(reportDto.getId());
            List<TransferEntity> transferEntities = transferRepository.findByReportId(reportDto.getId());
            return new ReportResponse(reportDto, restDtos, transferEntities);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
