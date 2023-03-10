package com.example.itspower.repository;

import com.example.itspower.repository.repositoryjpa.ReasonJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportEmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportEmployeeRepository {
    private final ReportEmployeeJpaRepository employeeJpaRepository;
    private final ReasonJpaRepository reasonJpaRepository;
//
//    public ReportDtlEntity reportEmployee(Integer roleId, Integer teamId, EmpRequest request) {
//        ReportDtlEntity employee = new ReportDtlEntity();
//        employee.setId(request.getId());
////        employee.setRoleId(roleId);
////        employee.setTeamId(teamId);
////        employee.setEmployeeName(request.getEmployeeName());
////        employee.setWorkDate(DateUtils.fromString(request.getWorkDate()));
////        employee.setReasonId(request.getReasonId());
//        return employeeJpaRepository.save(employee);
//    }
}
