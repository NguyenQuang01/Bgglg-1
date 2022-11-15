package com.example.itspower.service.impl;


import com.example.itspower.entity.Employee;
import com.example.itspower.repository.EmployeeRepository;
import com.example.itspower.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Employee> getAll() {
        return repository.getAllByEmployeeIdIsNotNull();
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getByOrganizationId(Long orgId) {
        return repository.getByOrganizationId(orgId);
    }
}
