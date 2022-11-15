package com.example.itspower.repository;
import com.example.itspower.dto.search.CaseResultEmployeeDto;
import com.example.itspower.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    EmployeeDto getByEmployeeId(Long id);

    List<Employee> getAllByEmployeeIdIsNotNull();

    List<Employee> getByOrganizationId(Long orgId);

    @Query(value = "SELECT o.sys_organization_id,o.name from vhr_employee e inner join vhr_org o ON e.organization_id = o.sys_organization_id",nativeQuery = true)
    List<CaseResultEmployeeDto> getListEmployee(Pageable pageable);
}
