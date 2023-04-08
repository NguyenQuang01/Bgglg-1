package com.example.itspower.response.group;

import com.example.itspower.model.resultset.ViewAllDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class ViewDetailsResponse {
    private Integer groupId;
    private Integer groupParentId;
    private Integer reportId;
    private Integer riceId;
    private Integer transferId;
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
    private Integer transferType;

    List<ViewDetailsResponse> children;

    public ViewDetailsResponse(ViewAllDto viewAllDto){
        this.groupId=viewAllDto.getGroupId();
        this.groupParentId=viewAllDto.getGroupParentId();
        this.reportId=viewAllDto.getReportId();
        this.riceId=viewAllDto.getRiceId();
        this.transferId=viewAllDto.getTransferId();
        this.groupDemarcationAvailable=viewAllDto.getGroupDemarcationAvailable();
        this.groupName=viewAllDto.getGroupName();
        this.reportDate=viewAllDto.getReportDate();
        this.reportDemarcation=viewAllDto.getReportDemarcation();
        this.laborProductivity=viewAllDto.getLaborProductivity();
        this.partTimeNum=viewAllDto.getPartTimeNum();
        this.restNum=viewAllDto.getRestNum();
        this.studentNum=viewAllDto.getStudentNum();
        this.riceCus=viewAllDto.getRiceCus();
        this.riceEmp=viewAllDto.getRiceEmp();
        this.riceVip=viewAllDto.getRiceVip();
        this.transferIsAccess=viewAllDto.getTransferIsAccess();
        this.transferDate=viewAllDto.getTransferDate();
        this.transferNum=viewAllDto.getTransferNum();
        this.transferType=viewAllDto.getTransferType();
    }
}
