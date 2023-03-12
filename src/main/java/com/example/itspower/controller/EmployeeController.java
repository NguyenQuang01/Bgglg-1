package com.example.itspower.controller;

import com.example.itspower.response.request.EmployeeRequest;
import com.example.itspower.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("employee/save")
    public ResponseEntity<Object> save(@RequestBody List<@Valid EmployeeRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(requests));
    }
}
