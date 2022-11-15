package com.example.itspower.controller;

import com.example.itspower.dto.search.CaseResultEmployeeDto;
import com.example.itspower.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/getEmployee")
    public List<CaseResultEmployeeDto> getCaseEmployee(Pageable pageable){
        return employeeRepository.getListEmployee(pageable);
    }
}
