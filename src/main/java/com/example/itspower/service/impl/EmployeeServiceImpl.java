package com.example.itspower.service.impl;

import com.example.itspower.model.entity.EmployeeRestEntity;
import com.example.itspower.repository.EmployeeRepository;
import com.example.itspower.response.EmployeeResponse;
import com.example.itspower.response.request.EmployeeRequest;
import com.example.itspower.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> saveEmployee(List<EmployeeRequest> requests) {
        List<EmployeeRestEntity> employeeRestEntities = employeeRepository.saveEmployees(requests);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        employeeRestEntities.forEach(i -> employeeResponses.add(new EmployeeResponse(i)));
        return employeeResponses;
    }
}
