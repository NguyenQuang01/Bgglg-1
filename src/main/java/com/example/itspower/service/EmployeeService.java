package com.example.itspower.service;

import com.example.itspower.response.EmployeeResponse;
import com.example.itspower.response.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> saveEmployee(List<EmployeeRequest> requests);
}
