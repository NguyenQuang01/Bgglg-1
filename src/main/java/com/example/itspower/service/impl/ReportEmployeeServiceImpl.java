package com.example.itspower.service.impl;

import com.example.itspower.model.ReportEmployeeResponse;
import com.example.itspower.repository.ReportEmployeeRepository;
import com.example.itspower.response.request.EmpRequest;
import com.example.itspower.response.request.EmployeeRequest;
import com.example.itspower.service.ReportEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportEmployeeServiceImpl implements ReportEmployeeService {
    @Autowired
    private ReportEmployeeRepository reportEmployeeRepository;

    public ReportEmployeeResponse updateReportEmployee(Integer roleId, Integer teamId, EmployeeRequest request) {
        List<EmpRequest> empRequests = request.getRequests();
        for (EmpRequest itemRequest : empRequests){

        }
        return null;
    }
}
