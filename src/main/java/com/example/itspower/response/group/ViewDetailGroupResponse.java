package com.example.itspower.response.group;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ViewDetailGroupResponse {
    private Integer groupKey;
    private String name;
    private Integer parentId = 0;
    private Integer demarcation;
    private Integer laborProductivity;
    private Integer restEmp;
    private Integer partTimeEmp;
    private Integer studentNum;
    private Integer riceCus;
    private Integer riceVip;
    private Integer riceEmp;
    private Integer totalRiceNum;
    private List<ViewDetailGroupResponse> children;

    public ViewDetailGroupResponse(Integer groupKey, String name, Integer parentId, Integer demarcation, Integer laborProductivity, Integer restEmp, Integer partTimeEmp, Integer studentNum, Integer riceCus, Integer riceVip, Integer riceEmp, Integer totalRiceNum) {
        this.groupKey = groupKey;
        this.name = name;
        this.parentId = parentId == null ? 0 : parentId;
        this.demarcation = demarcation == null ? 0 : demarcation;
        this.laborProductivity = laborProductivity == null ? 0 : laborProductivity;
        this.restEmp = restEmp == null ? 0 : restEmp;
        this.partTimeEmp = partTimeEmp == null ? 0 : partTimeEmp;
        this.studentNum = studentNum == null ? 0 : studentNum;
        this.riceCus = riceCus == null ? 0 : riceCus;
        this.riceVip = riceVip == null ? 0 : riceVip;
        this.riceEmp = riceEmp == null ? 0 : riceEmp;
        this.totalRiceNum = totalRiceNum == null ? 0 : totalRiceNum;
    }
}
