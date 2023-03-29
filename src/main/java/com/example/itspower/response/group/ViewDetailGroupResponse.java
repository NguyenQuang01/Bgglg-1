package com.example.itspower.response.group;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    public ViewDetailGroupResponse(Integer groupKey, String name, Integer parentId, Integer demarcation, Integer laborProductivity, Integer restEmp, Integer partTimeEmp, Integer studentNum, Integer riceCus, Integer riceVip, Integer riceEmp, Integer totalRiceNum) {
        this.groupKey = groupKey;
        this.name = name;
        this.parentId = parentId == null ? 0 : parentId;
        this.demarcation = demarcation == null ? 0 : demarcation;
        this.laborProductivity = laborProductivity;
        this.restEmp = restEmp;
        this.partTimeEmp = partTimeEmp;
        this.studentNum = studentNum;
        this.riceCus = riceCus;
        this.riceVip = riceVip;
        this.riceEmp = riceEmp;
        this.totalRiceNum = totalRiceNum;
    }
}
