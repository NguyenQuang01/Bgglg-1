package com.example.itspower.service.impl;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.entity.RiceEntity;
import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.repository.RestRepository;
import com.example.itspower.repository.RiceRepository;
import com.example.itspower.repository.TransferRepository;
import com.example.itspower.request.ReportRequest;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private RestRepository restRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private RiceRepository riceRepository;

    public ReportResponse reportDto(String reportDate) {
        try {
            ReportDto reportDto = reportRepository.reportDto(reportDate);
            List<RestDto> restDtos = restRepository.getRests(reportDto.getId());
            List<TransferEntity> transferEntities = transferRepository.findByReportId(reportDto.getId());
            RiceEntity riceEntity = riceRepository.getByRiceDetail(reportDto.getId());
            return new ReportResponse(reportDto, riceEntity, restDtos, transferEntities);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ReportResponse save(ReportRequest request) {
        Optional<ReportEntity> entity = reportRepository.findByReportDate(DateUtils.formatDate(new Date()));
        if (entity.isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "report date is exits", HttpStatus.NOT_FOUND.name());
        }
        if (request.getRestNum() == null || request.getRestNum() != request.getRestRequests().size()) {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "size rest not equal size effective", HttpStatus.BAD_REQUEST.name());
        }
        ReportEntity reportEntity = reportRepository.saveReport(request);
        riceRepository.saveRice(request.getRiceRequests(), reportEntity.getId());
        restRepository.saveRest(request.getRestRequests(), reportEntity.getId());
        transferRepository.saveTransfer(request.getTransferRequests(), reportEntity.getId());
        return reportDto(DateUtils.formatDate(reportEntity.getReportDate()));
    }
}
