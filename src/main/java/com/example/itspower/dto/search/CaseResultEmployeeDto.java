package com.example.itspower.dto.search;


import com.example.itspower.entity.Organization;

public class CaseResultEmployeeDto {
    private Long sysOrganizationId;

    private String name;
    public CaseResultEmployeeDto(Organization o) {
        this.sysOrganizationId = o.getSysOrganizationId();
        this.name = o.getName();
    }
    public CaseResultEmployeeDto(Organization o, boolean parent) {
        this.sysOrganizationId = o.getSysOrganizationId();
        this.name = o.getName();
    }
}
