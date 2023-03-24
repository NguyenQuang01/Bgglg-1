package com.example.itspower.service.impl;

import com.example.itspower.component.util.DateUtils;
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
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> reportDto(String reportDate, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(reportDate, groupId);
        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "report is not exits now date", HttpStatus.BAD_REQUEST.name()));
        }
        ReportDto reportDto = reportRepository.reportDto(reportDate, groupId);
        List<RestDto> restDtos = restRepository.getRests(reportDto.getId());
        List<TransferEntity> transferEntities = transferRepository.findByReportId(reportDto.getId());
        RiceEntity riceEntity = riceRepository.getByRiceDetail(reportDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ReportResponse(reportDto, riceEntity, restDtos, transferEntities));
    }

    @Override
    public ResponseEntity<Object> save(ReportRequest request, int groupId) {
        try {
            Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(DateUtils.formatDate(new Date()), groupId);
            if (entity.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "report date is exits", HttpStatus.BAD_REQUEST.name()));
            }
            if (request.getRestNum() != request.getRestRequests().size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "size rest not equal size effective", HttpStatus.BAD_REQUEST.name()));
            }
            ReportEntity reportEntity = reportRepository.saveReport(request, groupId);
            riceRepository.saveRice(request.getRiceRequests(), reportEntity.getId());
            restRepository.saveRest(request.getRestRequests(), reportEntity.getId());
            transferRepository.saveTransfer(request.getTransferRequests(), reportEntity.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId()));
        } catch (Exception e) {
            throw new RuntimeException(HttpStatus.BAD_GATEWAY.name());
        }
    }

    @Override
    public ResponseEntity<Object> update(ReportRequest request, int groupId) {
        try {
            Optional<ReportEntity> entity = reportRepository.findByIdAndGroupId(request.getId(), groupId);
            if (entity.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "report is not Exits", HttpStatus.BAD_REQUEST.name()));
            }
            ReportEntity reportEntity = reportRepository.updateReport(request, groupId);
            riceRepository.updateRice(request.getRiceRequests(), reportEntity.getId());
            restRepository.updateRest(request.getRestRequests(), reportEntity.getId());
            transferRepository.updateTransfer(request.getTransferRequests(), reportEntity.getId(), groupId);
            return ResponseEntity.status(HttpStatus.OK).body(reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId()));
        } catch (Exception e) {
            throw new RuntimeException(HttpStatus.BAD_GATEWAY.name());
        }
    }
}