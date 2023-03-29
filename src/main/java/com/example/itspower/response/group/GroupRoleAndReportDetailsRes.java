package com.example.itspower.response.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRoleAndReportDetailsRes {
    private Integer groupId;
    private Integer parentId;
    private String groupName;
    private Integer laborProductivity;
    private Integer partTimeNum;
    private Integer restNum;
    private Integer riceCus;
    private Integer riceVip;
    private Integer riceEmp;
    private Integer studentNum;
    private Integer demarcationAvailable;
    private Integer totalDemarcationAvailable;
    List<GroupRoleAndReportDetailsRes> children;


    public GroupRoleAndReportDetailsRes(InterfaceReportDetails item) {
        this.groupId = item.getGroupId();
        this.parentId = item.getParentId();
        this.groupName = item.getGroupName();
        this.laborProductivity = item.getLaborProductivity();
        this.partTimeNum = item.getPartTimeEmp();
        this.restNum = item.getRestEmp();
        this.studentNum = item.getStudentNum();
        this.riceCus = item.getRiceCus();
        this.riceVip = item.getRiceVip();
        this.riceEmp = item.getRiceEmp();
        this.laborProductivity = item.getLaborProductivity();
        this.demarcationAvailable = item.getTotalEmpDemarcation();
    }
}
