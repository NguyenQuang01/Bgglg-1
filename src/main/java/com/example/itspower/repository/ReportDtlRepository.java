package com.example.itspower.repository;

import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.repository.repositoryjpa.ReportDtlJpaRepository;
import com.example.itspower.response.request.ReportDtlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportDtlRepository {
    private final ReportDtlJpaRepository reportDtlJpaRepository;

    public ReportDtlEntity saveDtls(ReportDtlRequest reportDtlRequest, Integer reportId) {
        ReportDtlEntity reportDtlEntity = new ReportDtlEntity();
        reportDtlEntity.setReportId(reportId);
        reportDtlEntity.setEmpNum(reportDtlRequest.getEmpNum());
        reportDtlEntity.setRiceNumber(reportDtlRequest.getRiceNumber());
        reportDtlEntity.setNumEmp(reportDtlRequest.getNumEmp());
        reportDtlEntity.setPartTimeNum(reportDtlRequest.getPartTimeNum());
        reportDtlEntity.setStudentNum(reportDtlRequest.getStudentNum());
        reportDtlEntity.setRestNumber(reportDtlRequest.getRestNumber());
        return reportDtlJpaRepository.save(reportDtlEntity);
    }

}
