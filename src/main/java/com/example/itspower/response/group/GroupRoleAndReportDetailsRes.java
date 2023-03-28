package com.example.itspower.response.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupRoleAndReportDetailsRes {
    private Integer groupId;
    private Integer parentId;
    private Integer userGroupId;
    private Integer userId;
    private Integer reportId;
    private String groupName;
    private Integer reportDemarcation;
    private Integer laborProductivity;
    private Integer partTimeNum;
    private String reportDate;
    private Integer restNum;
    private Integer studentNum;
    private Integer demarcationAvailable;
    List<GroupRoleAndReportDetailsRes> children;

    public GroupRoleAndReportDetailsRes(InterfaceReportDetails item) {
        this.groupId = item.getGroupId();
        this.parentId = item.getParentId();
        this.userGroupId = item.getUserGroupId();
        this.userId = item.getUserId();
        this.reportId = item.getReportId();
        this.groupName = item.getGroupName();
        this.reportDemarcation = item.getReportDemarcation();
        this.laborProductivity = item.getLaborProductivity();
        this.partTimeNum = item.getPartTimeNum();
        this.reportDate = item.getReportDate();
        this.restNum = item.getRestNum();
        this.studentNum = item.getStudentNum();
        this.demarcationAvailable = item.getDemarcationAvailable();
    }
}
