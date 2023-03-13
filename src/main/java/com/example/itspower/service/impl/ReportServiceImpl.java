package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.ReportDtlRepository;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.repository.repositoryjpa.GroupRepository;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportDtlRepository reportDtlRepository;
    @Autowired
    private GroupRepository groupRepository;


    @Override
    public ReportResponse add(ReportRequest request) {
        try {
            ReportEntity reportEntity = reportRepository.save(request);
            ReportDtlEntity dtlEntities = reportDtlRepository.saveDtls(request.getReportDtlRequest(), reportEntity.getId());
            return new ReportResponse(reportEntity, dtlEntities);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<GroupEntity> getListGroup() {
        return groupRepository.findAll();
    }
}
