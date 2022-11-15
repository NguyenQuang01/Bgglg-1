package com.example.itspower.dto.search;

import com.example.itspower.entity.Organization;
import lombok.Data;

@Data
public class OrganizationDto {
    private Long sysOrganizationId;
    private String code;
    private String name;
    private Long orgParentId;
    private OrganizationDto orgParent;
    private String orgAddress;

    public OrganizationDto(Organization o) {
        this.sysOrganizationId = o.getSysOrganizationId();
        this.code = o.getCode();
        this.name = o.getName();
        this.orgParentId = o.getOrgParentId();
        this.orgAddress = o.getOrgAddress();
        if (o.getOrgParent() != null) {
            this.orgParent = new OrganizationDto(o.getOrgParent(),true);
        }
    }

    public OrganizationDto(Organization o, boolean parent) {
        this.sysOrganizationId = o.getSysOrganizationId();
        this.code = o.getCode();
        this.name = o.getName();
        this.orgParentId = o.getOrgParentId();
        this.orgAddress = o.getOrgAddress();
    }
}
