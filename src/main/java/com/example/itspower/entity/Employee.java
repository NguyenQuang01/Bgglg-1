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
@Table(name = "vhr_employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMPLOYEE_ID", insertable = false, updatable = false)
    private Long employeeId;

    @Column(name = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Long organizationId;

    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @Column(name = "EMPLOYEE_CODE", insertable = false, updatable = false)
    private String employeeCode;

    @JsonIgnore
    @Column(name = "GENDER", insertable = false, updatable = false)
    private Integer gender;

    @JsonIgnore
    @Column(name = "DATE_OF_BIRTH", insertable = false, updatable = false)
    private Date dateOfBirth;

    @JsonIgnore
    @Column(name = "PLACE_OF_BIRTH", insertable = false, updatable = false)
    private String placeOfBirth;

    @JsonIgnore
    @Column(name = "CURRENT_ADDRESS", insertable = false, updatable = false)
    private String currentAddress;

    @JsonIgnore
    @Column(name = "PERMANENT_ADDRESS", insertable = false, updatable = false)
    private String permanentAddress;

    @JsonIgnore
    @Column(name = "POSITION_ID", insertable = false, updatable = false)
    private Long positionId;

    @Column(name = "POSITION_NAME", insertable = false, updatable = false)
    private String positionName;

    @Column(name = "FULL_NAME", insertable = false, updatable = false)
    private String fullName;

    @Column(name = "MOBILE_PHONE", insertable = false, updatable = false)
    private String mobilePhone;

    @Column(name = "TELEPHONE", insertable = false, updatable = false)
    private String telephone;

    @JsonIgnore
    @Column(name = "EMAIL", insertable = false, updatable = false)
    private String email;

    @JsonIgnore
    @Column(name = "USER_NAME", insertable = false, updatable = false)
    private String user_name;

    @JsonIgnore
    @Column(name = "PASSWORD", insertable = false, updatable = false)
    private String password;

    @Column(name = "DISPLAY_NAME", insertable = false, updatable = false)
    private String displayName;

    @JsonIgnore
    @Column(name = "LANGUAGE", insertable = false, updatable = false)
    private Long language;

    @JsonIgnore
    @Column(name = "TIMEZONE", insertable = false, updatable = false)
    private Long timezone;

    @JsonIgnore
    @Column(name = "STATUS", insertable = false, updatable = false)
    private Integer status;

    @JsonIgnore
    @Column(name = "PHONE_SMS", insertable = false, updatable = false)
    private String phoneSms;

    @JsonIgnore
    @Column(name = "PROFILE_IMAGE_PATH", insertable = false, updatable = false)
    private String profileImagePath;

    @JsonIgnore
    @Column(name = "USER_LANGUAGE", insertable = false, updatable = false)
    private String userLanguage;

    @JsonIgnore
    @Column(name = "FILE_CER", insertable = false, updatable = false)
    private String fileCer;

    @JsonIgnore
    @Column(name = "IS_ACTIVE", insertable = false, updatable = false)
    private Integer isActive;

    @JsonIgnore
    @Column(name = "PARTY_ADMISSION_DATE", insertable = false, updatable = false)
    private Date partyAdmissionDate;

    @JsonIgnore
    @Column(name = "PARTY_ADMISSION_PLACE", insertable = false, updatable = false)
    private String partyAdmissionPlace;

    @JsonIgnore
    @Column(name = "SOLDIER_LEVEL", insertable = false, updatable = false)
    private String soldierLevel;

    @JsonIgnore
    @Column(name = "EMP_TYPE", insertable = false, updatable = false)
    private String empType;

    @JsonIgnore
    @Column(name = "EMP_TYPE_ID", insertable = false, updatable = false)
    private String empTypeId;

    @JsonIgnore
    @Column(name = "SALE_CODE", insertable = false, updatable = false)
    private String saleCode;

    @JsonIgnore
    @Column(name = "COLLECT_CALL_CODE", insertable = false, updatable = false)
    private String collectCallCode;

    @JsonIgnore
    @Column(name = "ACCOUNT_NUMBER", insertable = false, updatable = false)
    private String accountNumber;

    @JsonIgnore
    @Column(name = "BANK_BRANCH", insertable = false, updatable = false)
    private String bankBranch;

    @JsonIgnore
    @Column(name = "BANK", insertable = false, updatable = false)
    private String bank;

    @JsonIgnore
    @Column(name = "EMAIL_SIGN", insertable = false, updatable = false)
    private String emailSign;

    @JsonIgnore
    @Column(name = "SIM_VERSION", insertable = false, updatable = false)
    private Integer simVersion;

    @JsonIgnore
    @Column(name = "PHONE_CHAT", insertable = false, updatable = false)
    private String phoneChat;

    @JsonIgnore
    @Column(name = "VOFFICE1_USER", insertable = false, updatable = false)
    private String voffice1User;

    @JsonIgnore
    @Column(name = "VOFFICE1_PASS", insertable = false, updatable = false)
    private String voffice1Pass;

    @JsonIgnore
    @Column(name = "EMP_LEVEL", insertable = false, updatable = false)
    private Integer empLevel;

    @JsonIgnore
    @Column(name = "ALIAS_NAME", insertable = false, updatable = false)
    private String aliasName;

    @JsonIgnore
    @Column(name = "TIME_ZONE_ID", insertable = false, updatable = false)
    private Integer timeZoneId;

    @JsonIgnore
    @Column(name = "INDEXING_STATE", insertable = false, updatable = false)
    private Integer indexingState;

    @JsonIgnore
    @Column(name = "IS_FOREIGN_EMPLOYEE", insertable = false, updatable = false)
    private Integer isForeignEmployee;

    @JsonIgnore
    @Column(name = "HAS_CHANGE_ORG", insertable = false, updatable = false)
    private Integer hasChangeOrg;

    @JsonIgnore
    @Column(name = "HAS_CHANGE_ORG_DATE", insertable = false, updatable = false)
    private Date hasChangeOrgDate;

    @JsonIgnore
    @Column(name = "SYNC_DATE", insertable = false, updatable = false)
    private Date syncDate;

    @JsonIgnore
    @Column(name = "CREATED_DATE", insertable = false, updatable = false)
    private Date createdDate;

    @JsonIgnore
    @Column(name = "CREATED_BY", insertable = false, updatable = false)
    private Long createdBy;

    @JsonIgnore
    @Column(name = "UPDATED_DATE", insertable = false, updatable = false)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "UPDATED_BY", insertable = false, updatable = false)
    private Long updatedBy;

    @JsonIgnore
    @Column(name = "DELETED_DATE", insertable = false, updatable = false)
    private Date deletedDate;

    @JsonIgnore
    @Column(name = "DELETED_BY", insertable = false, updatable = false)
    private Long deletedBy;

    @JsonIgnore
    @Column(name = "DEL_FLAG", insertable = false, updatable = false)
    private Integer delFlag;

    @JsonIgnore
    @Column(name = "ACTION_TYPE", insertable = false, updatable = false)
    private String actionType;

    @JsonIgnore
    @Column(name = "IDENTIFICATION", insertable = false, updatable = false)
    private String identification;

    @JsonIgnore
    @Column(name = "FIRST_NAME", insertable = false, updatable = false)
    private String firstName;

    @JsonIgnore
    @Column(name = "MIDDLE_NAME", insertable = false, updatable = false)
    private String middleName;

    @JsonIgnore
    @Column(name = "LAST_NAME", insertable = false, updatable = false)
    private String lastName;

    @Column(name = "USERNAME", insertable = false, updatable = false)
    private String username;

    @JsonIgnore
    @Column(name = "ISTYPE", insertable = false, updatable = false)
    private String isType;

    @JsonIgnore
    @Column(name = "SYSN_TYPE", insertable = false, updatable = false)
    private String sysnType;

    @JsonIgnore
    @Column(name = "CA_SIM_PHONE_NUMBER", insertable = false, updatable = false)
    private String caSimPhoneNumber;

    @JsonIgnore
    @Column(name = "CA_SERIAL", insertable = false, updatable = false)
    private String caSerial;

    @JsonIgnore
    @Column(name = "SIMCA_VERSION", insertable = false, updatable = false)
    private Integer simcaVersion;

    @JsonIgnore
    @Column(name = "ORDER_NUMBER", insertable = false, updatable = false)
    private Integer orderNumber;

    @JsonIgnore
    @Column(name = "SIGNUSBV2", insertable = false, updatable = false)
    private Integer signusbv2;
}
