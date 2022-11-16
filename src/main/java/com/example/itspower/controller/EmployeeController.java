package com.example.itspower.controller;
import com.example.itspower.entity.Employee;
import com.example.itspower.repository.EmployeeRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/getEmployee")
    public ResponseEntity<List<Employee>> getCaseEmployee() {
        try {
            List<Employee> employees = new ArrayList<Employee>();
            employeeRepository.findAll().forEach(employees::add);
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            throw new NullPointerException("can't get Employee" + e);
        }
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/update/{id}")
    public SuccessResponse<Employee> updateEmployee(@PathVariable("id") Long id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (!employee.isEmpty()) {
                Employee updateE = employeeService.creatOrUpdate(employee.get());
                return new SuccessResponse<>(1, "update employee success", "update.success", HttpStatus.CREATED);
            } else {
                return new SuccessResponse<>(0, "cannot find employeeId :", "update.fail", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new SuccessResponse<>(0, "error update employee :" + e, "update.fail", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create")
    public SuccessResponse<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.creatOrUpdate(employee);
            return new SuccessResponse<>(1, "create success", employee.getEmployeeId(), "insert.success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new SuccessResponse<>(0, "error create employee :" + e, "insert.fail", HttpStatus.BAD_REQUEST);
        }
    }
}
