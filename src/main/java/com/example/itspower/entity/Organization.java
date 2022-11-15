package com.example.itspower.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vhr_org")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SYS_ORGANIZATION_ID", insertable = false, updatable = false)
    private Long sysOrganizationId;

    @Column(name = "CODE", insertable = false, updatable = false)
    private String code;

    @Column(name = "NAME", insertable = false, updatable = false)
    private String name;

    @JsonIgnore
    @Column(name = "TYPE", insertable = false, updatable = false)
    private String type;

    @JsonIgnore
    @Column(name = "PATH", insertable = false, updatable = false)
    private String path;

    @Column(name = "ORG_PARENT_ID", insertable = false, updatable = false)
    private Long orgParentId;

    @ManyToOne
    @JoinColumn(name = "ORG_PARENT_ID", referencedColumnName = "SYS_ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization orgParent;

    @JsonIgnore
    @Column(name = "ORDER_NUMBER", insertable = false, updatable = false)
    private Long orderNumber;

    @JsonIgnore
    @Column(name = "ORG_LEVEL", insertable = false, updatable = false)
    private Long orgLevel;

    @JsonIgnore
    @Column(name = "ORG_LEVEL_MANAGE", insertable = false, updatable = false)
    private Long orgLevelManage;

    @JsonIgnore
    @Column(name = "ORG_CODE_PATH", insertable = false, updatable = false)
    private String orgCodePath;

    @JsonIgnore
    @Column(name = "EFFECTIVE_END_DATE", insertable = false, updatable = false)
    private Date effectiveEndDate;

    @JsonIgnore
    @Column(name = "EFFECTIVE_START_DATE", insertable = false, updatable = false)
    private Date effectiveStartDate;

    @JsonIgnore
    @Column(name = "CREATED_BY", insertable = false, updatable = false)
    private Long createdBy;

    @JsonIgnore
    @Column(name = "CREATED_DATE", insertable = false, updatable = false)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "UPDATED_BY", insertable = false, updatable = false)
    private Long updatedBy;

    @JsonIgnore
    @Column(name = "UPDATED_DATE", insertable = false, updatable = false)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "DELETED_BY", insertable = false, updatable = false)
    private Long deletedBy;

    @JsonIgnore
    @Column(name = "DELETED_DATE", insertable = false, updatable = false)
    private Date deletedDate;

    @JsonIgnore
    @Column(name = "DEL_FLAG", insertable = false, updatable = false)
    private Long delFlag;

    @JsonIgnore
    @Column(name = "STATE", insertable = false, updatable = false)
    private Long state;

    @JsonIgnore
    @Column(name = "GENERATE_SORT_ORDER", insertable = false, updatable = false)
    private String generateSortOrder;

    @JsonIgnore
    @Column(name = "ORG_LOOKUP", insertable = false, updatable = false)
    private Long orgLookup;

    @JsonIgnore
    @Column(name = "IS_LEAF", insertable = false, updatable = false)
    private Long isLeaf;

    @JsonIgnore
    @Column(name = "PATH_NAME", insertable = false, updatable = false)
    private String pathName;

    @JsonIgnore
    @Column(name = "HAVE_DOCUMENT_MANAGER", insertable = false, updatable = false)
    private Long haveDocumentManager;

    @JsonIgnore
    @Column(name = "ORDER_VIEW", insertable = false, updatable = false)
    private Long orderView;

    @JsonIgnore
    @Column(name = "IS_ACTIVE", insertable = false, updatable = false)
    private Long isActive;

    @JsonIgnore
    @Column(name = "ABBREVIATION", insertable = false, updatable = false)
    private String abbreviation;

    @JsonIgnore
    @Column(name = "HAS_EMPLOYEE", insertable = false, updatable = false)
    private Long hasEmployee;

    @JsonIgnore
    @Column(name = "GROUPID_VOF1", insertable = false, updatable = false)
    private Long groupidVof1;

    @JsonIgnore
    @Column(name = "GROUPNAME_VOF1", insertable = false, updatable = false)
    private String groupnameVof1;

    @JsonIgnore
    @Column(name = "ISCHECK", insertable = false, updatable = false)
    private Long isCheck;

    @JsonIgnore
    @Column(name = "INDEXING_STATE", insertable = false, updatable = false)
    private Long indexingState;

    @JsonIgnore
    @Column(name = "SYNC_DATE", insertable = false, updatable = false)
    private Date syncDate;

    @JsonIgnore
    @Column(name = "ACTION_TYPE", insertable = false, updatable = false)
    private String actionType;

    @JsonIgnore
    @Column(name = "ORGID", insertable = false, updatable = false)
    private String orgId;

    @JsonIgnore
    @Column(name = "PARENTID_MB", insertable = false, updatable = false)
    private String parentidMb;

    @JsonIgnore
    @Column(name = "SYSN_TYPE", insertable = false, updatable = false)
    private String sysnType;

    @JsonIgnore
    @Column(name = "ISTYPE", insertable = false, updatable = false)
    private String isType;

    @JsonIgnore
    @Column(name = "USERNAME", insertable = false, updatable = false)
    private String username;

    @JsonIgnore
    @Column(name = "ORG_TYPE", insertable = false, updatable = false)
    private String orgType;

    @Column(name = "ORG_ADDRESS", insertable = false, updatable = false)
    private String orgAddress;

    public Organization(Long sysOrganizationId, String name) {
        this.sysOrganizationId = sysOrganizationId;
        this.name = name;
    }
}