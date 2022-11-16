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
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @ManyToOne
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;

    @JsonIgnore
    @Column(name = "GENDER")
    private Integer gender;

    @JsonIgnore
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @JsonIgnore
    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @JsonIgnore
    @Column(name = "CURRENT_ADDRESS")
    private String currentAddress;

    @JsonIgnore
    @Column(name = "PERMANENT_ADDRESS")
    private String permanentAddress;

    @JsonIgnore
    @Column(name = "POSITION_ID")
    private Long positionId;

    @Column(name = "POSITION_NAME")
    private String positionName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    @Column(name = "TELEPHONE")
    private String telephone;

    @JsonIgnore
    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "USER_NAME")
    private String user_name;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @JsonIgnore
    @Column(name = "LANGUAGE")
    private Long language;

    @JsonIgnore
    @Column(name = "TIMEZONE")
    private Long timezone;

    @JsonIgnore
    @Column(name = "STATUS")
    private Integer status;

    @JsonIgnore
    @Column(name = "PHONE_SMS")
    private String phoneSms;

    @JsonIgnore
    @Column(name = "PROFILE_IMAGE_PATH")
    private String profileImagePath;

    @JsonIgnore
    @Column(name = "USER_LANGUAGE")
    private String userLanguage;

    @JsonIgnore
    @Column(name = "FILE_CER")
    private String fileCer;

    @JsonIgnore
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @JsonIgnore
    @Column(name = "PARTY_ADMISSION_DATE")
    private Date partyAdmissionDate;

    @JsonIgnore
    @Column(name = "PARTY_ADMISSION_PLACE")
    private String partyAdmissionPlace;

    @JsonIgnore
    @Column(name = "SOLDIER_LEVEL")
    private String soldierLevel;

    @JsonIgnore
    @Column(name = "EMP_TYPE")
    private String empType;

    @JsonIgnore
    @Column(name = "EMP_TYPE_ID")
    private String empTypeId;

    @JsonIgnore
    @Column(name = "SALE_CODE")
    private String saleCode;

    @JsonIgnore
    @Column(name = "COLLECT_CALL_CODE")
    private String collectCallCode;

    @JsonIgnore
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @JsonIgnore
    @Column(name = "BANK_BRANCH")
    private String bankBranch;

    @JsonIgnore
    @Column(name = "BANK")
    private String bank;

    @JsonIgnore
    @Column(name = "EMAIL_SIGN")
    private String emailSign;

    @JsonIgnore
    @Column(name = "SIM_VERSION")
    private Integer simVersion;

    @JsonIgnore
    @Column(name = "PHONE_CHAT")
    private String phoneChat;

    @JsonIgnore
    @Column(name = "VOFFICE1_USER")
    private String voffice1User;

    @JsonIgnore
    @Column(name = "VOFFICE1_PASS")
    private String voffice1Pass;

    @JsonIgnore
    @Column(name = "EMP_LEVEL")
    private Integer empLevel;

    @JsonIgnore
    @Column(name = "ALIAS_NAME")
    private String aliasName;

    @JsonIgnore
    @Column(name = "TIME_ZONE_ID")
    private Integer timeZoneId;

    @JsonIgnore
    @Column(name = "INDEXING_STATE")
    private Integer indexingState;

    @JsonIgnore
    @Column(name = "IS_FOREIGN_EMPLOYEE")
    private Integer isForeignEmployee;

    @JsonIgnore
    @Column(name = "HAS_CHANGE_ORG")
    private Integer hasChangeOrg;

    @JsonIgnore
    @Column(name = "HAS_CHANGE_ORG_DATE")
    private Date hasChangeOrgDate;

    @JsonIgnore
    @Column(name = "SYNC_DATE")
    private Date syncDate;

    @JsonIgnore
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @JsonIgnore
    @Column(name = "CREATED_BY")
    private Long createdBy;

    @JsonIgnore
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "UPDATED_BY")
    private Long updatedBy;

    @JsonIgnore
    @Column(name = "DELETED_DATE")
    private Date deletedDate;

    @JsonIgnore
    @Column(name = "DELETED_BY")
    private Long deletedBy;

    @JsonIgnore
    @Column(name = "DEL_FLAG")
    private Integer delFlag;

    @JsonIgnore
    @Column(name = "ACTION_TYPE")
    private String actionType;

    @JsonIgnore
    @Column(name = "IDENTIFICATION")
    private String identification;

    @JsonIgnore
    @Column(name = "FIRST_NAME")
    private String firstName;

    @JsonIgnore
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @JsonIgnore
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USERNAME")
    private String username;

    @JsonIgnore
    @Column(name = "ISTYPE")
    private String isType;

    @JsonIgnore
    @Column(name = "SYSN_TYPE")
    private String sysnType;

    @JsonIgnore
    @Column(name = "CA_SIM_PHONE_NUMBER")
    private String caSimPhoneNumber;

    @JsonIgnore
    @Column(name = "CA_SERIAL")
    private String caSerial;

    @JsonIgnore
    @Column(name = "SIMCA_VERSION")
    private Integer simcaVersion;

    @JsonIgnore
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @JsonIgnore
    @Column(name = "SIGNUSBV2")
    private Integer signusbv2;
}
