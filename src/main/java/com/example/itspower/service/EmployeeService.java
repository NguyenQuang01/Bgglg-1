package com.example.itspower.service;
import com.example.itspower.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Long id);

    List<Employee> getByOrganizationId(Long orgId);
}
