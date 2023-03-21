package com.example.itspower.repository;

import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.request.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Component
public class ReportRepository {
    @Autowired
    private ReportJpaRepository reportJpaRepository;

    public ReportDto reportDto(String reportDate, int groupId) {
        return reportJpaRepository.findByReport(reportDate, groupId);
    }

    public ReportEntity saveReport(ReportRequest request, int groupId) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setDemarcation(request.getDemarcation());
        reportEntity.setGroupId(groupId);
        reportEntity.setRestNum(request.getRestNum());
        reportEntity.setStudentNum(request.getStudentNum());
        reportEntity.setLaborProductivity(request.getLaborProductivity());
        reportEntity.setPartTimeNum(request.getPartTimeNum());
        reportEntity.setReportDate(new Date());
        return reportJpaRepository.save(reportEntity);
    }

    @Transactional
    public ReportEntity updateReport(ReportRequest request, int groupId) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setId(request.getId());
        reportEntity.setDemarcation(request.getDemarcation());
        reportEntity.setGroupId(groupId);
        reportEntity.setRestNum(request.getRestNum());
        reportEntity.setStudentNum(request.getStudentNum());
        reportEntity.setLaborProductivity(request.getLaborProductivity());
        reportEntity.setPartTimeNum(request.getPartTimeNum());
        reportEntity.setReportDate(new Date());
        return reportJpaRepository.save(reportEntity);
    }

    public Optional<ReportEntity> findByReportDate(String reportDate) {
        return reportJpaRepository.findByReportDate(reportDate);
    }

    public Optional<ReportEntity> findByReportDateAndGroupId(String reportDate, int groupId) {
        return reportJpaRepository.findByReportDateAndGroupId(reportDate, groupId);
    }

    public Optional<ReportEntity> findByIdAndGroupId(int id, int groupId) {
        return reportJpaRepository.findByIdAndGroupId(id, groupId);
    }
}
