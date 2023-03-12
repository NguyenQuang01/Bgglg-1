package com.example.itspower.service.impl;

import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.repository.ReportDtlRepository;
import com.example.itspower.repository.ReportRepository;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.ReportRestNumResponse;
import com.example.itspower.response.request.ReportDtlRequest;
import com.example.itspower.response.request.ReportEmpNumRequest;
import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportDtlRepository reportDtlRepository;

    @Override
    public ReportResponse add(Integer userGroupId, ReportRequest request) {
        List<ReportDtlRequest> entities = request.getRequests();
        ReportEntity entity = reportRepository.save(userGroupId, request);
        List<ReportDtlEntity> dtlEntities = reportDtlRepository.saveDtls(entities, entity.getId());
        return new ReportResponse(entity, dtlEntities);
    }

    @Override
    public ReportRestNumResponse reportRestNum(int userGroupId, int id, int restNum) {
        reportRepository.reportRestEmpNumber(id, userGroupId, restNum);
        Optional<ReportEntity> entity = reportRepository.findById(id);
        return new ReportRestNumResponse(entity);
    }

    public void reportEmpAndReason(List<ReportEmpNumRequest> requests, Integer userGroupId) {
        for (ReportEmpNumRequest numRequests : requests) {
            if (numRequests.getEmpNum() != null && numRequests.getEmpNum() > 0) {
                reportDtlRepository.reportEmAndReason(numRequests, userGroupId);
            }
        }
    }
}
