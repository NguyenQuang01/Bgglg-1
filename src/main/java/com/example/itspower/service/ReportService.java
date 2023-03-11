package com.example.itspower.service;

import com.example.itspower.response.ReportResponse;
import com.example.itspower.response.ReportRestNumResponse;
import com.example.itspower.response.request.ReportEmpNumRequest;
import com.example.itspower.response.request.ReportRequest;

import java.util.List;

public interface ReportService {
    ReportResponse add(Integer userGroupId, ReportRequest request);

    ReportRestNumResponse reportRestNum(int userGroupId, int id, int restNum);

    void reportEmpAndReason(List<ReportEmpNumRequest> requests, Integer userGroupId);
}
