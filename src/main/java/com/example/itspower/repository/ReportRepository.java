package com.example.itspower.repository;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.request.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportRepository {
    private final ReportJpaRepository reportJpaRepository;

    public ReportEntity save(Integer userGroupId, ReportRequest request) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setUserGroupId(userGroupId);
        reportEntity.setOrderDate(DateUtils.fromString(request.getOrderDate()));
//        reportEntity.setRestNumber(request.getRestNum());
        return reportJpaRepository.save(reportEntity);
    }

    public void reportRestEmpNumber(Integer id, Integer userGroupId, Integer restEmpNum) {
        reportJpaRepository.reportRestNumber(restEmpNum, id, userGroupId);
    }

    public Optional<ReportEntity> findById(int id) {
        return reportJpaRepository.findById(id);
    }
}
