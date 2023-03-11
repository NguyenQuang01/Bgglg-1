package com.example.itspower.repository;

import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.repositoryjpa.ReportDtlJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.request.ReportDtlRequest;
import com.example.itspower.response.request.ReportEmpNumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportDtlRepository {
    private final ReportDtlJpaRepository reportDtlJpaRepository;

    private final ReportJpaRepository reportJpaRepository;

    public List<ReportDtlEntity> saveDtls(List<ReportDtlRequest> reportDtlEntities, Integer reportId) {
        List<ReportDtlEntity> entities = new ArrayList<>();
        for (ReportDtlRequest item : reportDtlEntities) {
            ReportDtlEntity reportDtlEntity = new ReportDtlEntity();
            reportDtlEntity.setReportId(reportId);
            reportDtlEntity.setEmpNum(item.getEmpNum());
            reportDtlEntity.setEmpName(item.getEmpName());
            reportDtlEntity.setReasonId(item.getReasonId());
            reportDtlEntity.setPartTimeNum(item.getPartTimeNum());
            reportDtlEntity.setStudentNum(item.getStudentNum());
            reportDtlEntity.setRiceNumber(item.getRiceNum());
            entities.add(reportDtlEntity);
        }
        return reportDtlJpaRepository.saveAll(entities);
    }

    public void reportEmAndReason(ReportEmpNumRequest numRequest, Integer userGroupId) {
        Optional<ReportEntity> report = reportJpaRepository.findByIdAndUserGroupId(numRequest.getReportId(), userGroupId);
        Optional<ReportDtlEntity> reportDtl = reportDtlJpaRepository.findById(numRequest.getId());
        if (report.isPresent() && reportDtl.isPresent()) {
            reportDtlJpaRepository.reportEmpAndReason(numRequest.getEmpName(), numRequest.getEmpNum(), numRequest.getReasonId(), numRequest.getReportId(), numRequest.getId());
        }
    }

}
