package com.example.itspower.service.impl;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.entity.RiceEntity;
import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.*;
import com.example.itspower.request.ReportRequest;
import com.example.itspower.request.TransferRequest;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.report.ReportResponse;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private GroupRoleRepository groupRoleRepository;
    @Autowired
    private RiceRepository riceRepository;

    @Override
    public Object reportDto(String reportDate, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(reportDate, groupId);
        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "report is not exits now date", HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
        ReportDto reportDto = reportRepository.reportDto(reportDate, groupId);
        List<RestDto> restDtos = restRepository.getRests(reportDto.getId());
        List<TransferEntity> transferEntities = transferRepository.findByReportId(reportDto.getId());
        for (TransferEntity transfer : transferEntities) {
            Optional<GroupEntity> groupEntity = groupRoleRepository.findById(transfer.getGroupId());
            transfer.setParentId(groupEntity.get().getParentId());
            transfer.setGroupName(groupEntity.get().getGroupName());
        }
        RiceEntity riceEntity = riceRepository.getByRiceDetail(reportDto.getId());
        return new ReportResponse(reportDto, riceEntity, restDtos, transferEntities);
    }

    @Override
    public Object save(ReportRequest request, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByReportDateAndGroupId(DateUtils.formatDate(new Date()), groupId);
        if (entity.isPresent()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "report date is exits", HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        if (request.getRestNum() != request.getRestRequests().size()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "size rest not equal size effective", HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        for (TransferRequest transferRequests : request.getTransferRequests()) {
            Optional<GroupEntity> groupParent = groupRoleRepository.findByGroupName(transferRequests.getGroupParent());
            if (groupParent.isEmpty()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group parent is empty!", null);
            }
            Optional<GroupEntity> groupChild = groupRoleRepository.findByGroupNameAndParentId(transferRequests.getGroupName(), groupParent.get().getId());
            if (groupChild.isEmpty()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group name is empty!", null);
            }
            if (groupId == groupChild.get().getId()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group name not current group user!", null);
            }
        }
        ReportEntity reportEntity = reportRepository.saveReport(request, groupId);
        riceRepository.saveRice(request.getRiceRequests(), reportEntity.getId());
        restRepository.saveRest(request.getRestRequests(), reportEntity.getId());
        transferRepository.saveTransfer(request.getTransferRequests(), reportEntity.getId());
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.CREATED.value(), "report success", reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId())));
    }

    @Override
    @Transactional
    public Object update(ReportRequest request, int groupId) {
        Optional<ReportEntity> entity = reportRepository.findByIdAndGroupId(request.getId(), groupId);
        if (entity.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "report is not Exits", HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        if (request.getRestNum() != request.getRestRequests().size()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "size rest not equal size effective", HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
        for (TransferRequest transferRequests : request.getTransferRequests()) {
            if (transferRequests.getTransferId() == 0) {
                return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "transferId not exits", null);
            }
            Optional<GroupEntity> groupParent = groupRoleRepository.findByGroupName(transferRequests.getGroupParent());
            if (groupParent.isEmpty()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group parent is empty!", null);
            }
            Optional<GroupEntity> groupChild = groupRoleRepository.findByGroupNameAndParentId(transferRequests.getGroupName(), groupParent.get().getId());
            if (groupChild.isEmpty()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group name is empty!", null);
            }
            if (groupId == groupChild.get().getId()) {
                return new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "group name not current group user!", null);
            }
        }
        ReportEntity reportEntity = reportRepository.updateReport(request, groupId);
        riceRepository.updateRice(request.getRiceRequests(), reportEntity.getId());
        restRepository.updateRest(request.getRestRequests(), reportEntity.getId());
        transferRepository.updateTransfer(request.getTransferRequests(), reportEntity.getId());
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "update report success", reportDto(DateUtils.formatDate(reportEntity.getReportDate()), reportEntity.getGroupId())));
    }

    @Override
    public void deleteRestIdsAndReportId(Integer reportId, List<Integer> restIds) {
        restRepository.deleteRestIdsAndReportId(reportId, restIds);
    }
}