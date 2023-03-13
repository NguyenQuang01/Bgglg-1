package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.response.ReportDetailResponse;
import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.request.ReportRequest;

import java.util.List;

public interface ReportService {
    ReportResponse add(ReportRequest request);

    ReportDetailResponse detail(String orderDate, Integer userGroupId);

    List<GroupEntity> getListGroup();
}
