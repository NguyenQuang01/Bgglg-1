package com.example.itspower.dto;

import com.example.itspower.dto.search.OrganizationDto;
import com.example.itspower.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@Data
@NoArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    private OrganizationDto organization;
    private String employeeCode;
    private String positionName;
    private String fullName;
    private String mobilePhone;
    private String telephone;
    private String displayName;
    private String username;
    private Set<String> roles;

    public EmployeeDto(Employee e) {
        this.employeeId = e.getEmployeeId();
        if (e.getOrganization() != null) {
            this.organization = new OrganizationDto(e.getOrganization());
        }
        this.employeeCode = e.getEmployeeCode();
        this.positionName = e.getPositionName();
        this.fullName = e.getFullName();
        this.mobilePhone = e.getMobilePhone();
        this.telephone = e.getTelephone();
        this.displayName = e.getDisplayName();
        this.username = e.getUsername();
    }
}
