package com.example.itspower.model.resultset;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAllDto {

    private Integer groupId;
    private Integer groupParentId;
    private Integer reportId;
    private Integer riceId  ;
    private Integer transferId  ;
    private Integer groupDemarcationAvailable;
    private String groupName;
    private String reportDate;
    private Integer reportDemarcation;
    private Integer laborProductivity;
    private Integer partTimeNum;
    private Integer restNum;
    private Integer studentNum;
    private Integer riceCus;
    private Integer riceEmp;
    private Integer riceVip;
    private String transferIsAccess;
    private String transferDate;
    private Integer transferNum;
    private Integer supportNum;
    private Integer groupSupport;
}
