package com.example.itspower.service.impl;


import com.example.itspower.component.util.ObjectMappingUtils;
import com.example.itspower.entity.Employee;
import com.example.itspower.repository.EmployeeRepository;
import com.example.itspower.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public Employee creatOrUpdate( Employee employee) {
            Optional<Employee> findById = repository.findById(employee.getEmployeeId());
            if(!findById.isEmpty()){
                Employee updateEmployee = repository.findById(employee.getEmployeeId()).get();
                return repository.save(updateEmployee);
            }else{
                repository.save(employee);
            }
            return null;
    }
}
