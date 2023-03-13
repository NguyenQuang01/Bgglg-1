package com.example.itspower.repository;

import com.example.itspower.model.entity.EmployeeRestEntity;
import com.example.itspower.repository.repositoryjpa.EmployeeJpaRepository;
import com.example.itspower.response.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepository {
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    public List<EmployeeRestEntity> saveEmployees(List<EmployeeRequest> employeeRequests) {
        List<EmployeeRestEntity> employeeRestEntities = new ArrayList<>();
        for (EmployeeRequest item : employeeRequests) {
            EmployeeRestEntity entity = new EmployeeRestEntity();
            entity.setEmpName(item.getEmpName());
            entity.setEmpCode(item.getEmpCode());
            entity.setReasonId(item.getReasonId());
            entity.setReportId(item.getReportId());
            employeeRestEntities.add(entity);
        }
        return employeeJpaRepository.saveAll(employeeRestEntities);
    }
}
