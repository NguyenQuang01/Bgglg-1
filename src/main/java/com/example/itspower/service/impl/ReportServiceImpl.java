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

    @Override
    public ReportResponse reportDto(String reportDate, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(reportDate, groupId);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "", HttpStatus.BAD_REQUEST.name());
        }
        ReportDto reportDto = reportRepository.reportDto(reportDate, groupId);
        List<RestDto> restDtos = restRepository.getRests(reportDto.getId());
        List<TransferEntity> transferEntities = transferRepository.findByReportId(reportDto.getId());
        RiceEntity riceEntity = riceRepository.getByRiceDetail(reportDto.getId());
        return new ReportResponse(reportDto, riceEntity, restDtos, transferEntities);
    }

    @Override
    public ReportResponse save(ReportRequest request, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(DateUtils.formatDate(new Date()), groupId);
        if (entity.isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "report date is exits", HttpStatus.NOT_FOUND.name());
        }
        if (request.getRestNum() != request.getRestRequests().size()) {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "size rest not equal size effective", HttpStatus.BAD_REQUEST.name());
        }
        ReportEntity reportEntity = reportRepository.saveReport(request, groupId);
        riceRepository.saveRice(request.getRiceRequests(), reportEntity.getId());
        restRepository.saveRest(request.getRestRequests(), reportEntity.getId());
        transferRepository.saveTransfer(request.getTransferRequests(), reportEntity.getId(), groupId);
        return reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId());
    }

    @Override
    public ReportResponse update(ReportRequest request, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByIdAndGroupId(request.getId(), groupId);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "", HttpStatus.NOT_FOUND.name());
        }
        ReportEntity reportEntity = reportRepository.updateReport(request, groupId);
        riceRepository.updateRice(request.getRiceRequests(), reportEntity.getId());
        restRepository.updateRest(request.getRestRequests(), reportEntity.getId());
        transferRepository.updateTransfer(request.getTransferRequests(), reportEntity.getId(), groupId);
        return reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId());
    }
}
