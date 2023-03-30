package com.example.itspower.response.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewDetailGroups {
    private Integer key;
    private String name;
    private Integer parentId;
    private Integer office;
    private Integer enterprise;
    private Integer laborProductivity;
    private Integer totalLaborProductivity;
    private Integer numberLeave;
    private Integer partTimeEmp;
    private float ratio;
    private Integer studentNum;
    private Integer numberRice;
    private Integer riceCus;
    private Integer riceVip;
    private Integer riceEmp;
    List<ViewDetailGroups> children;

    public ViewDetailGroups viewDetailGroups(Integer numberLeave, Integer laborProductivity, Integer partTimeEmp, Integer studentNum, Integer numberRice, Integer riceCus, Integer riceVip, Integer riseEmp) {
        this.laborProductivity = laborProductivity;
        this.numberLeave = numberLeave;
        this.partTimeEmp = partTimeEmp;
        this.ratio = 0;
        this.studentNum = studentNum;
        this.numberRice = numberRice;
        this.riceCus = riceCus;
        this.riceVip = riceVip;
        this.riceEmp = riseEmp;
        return this;
    }

    public ViewDetailGroups(ViewDetailGroupResponse response) {
        this.key = response.getGroupKey();
        this.name = response.getName();
        this.parentId = response.getParentId();
        this.enterprise = response.getDemarcation();
        this.office = response.getDemarcation();
        this.laborProductivity = response.getLaborProductivity();
        this.numberLeave = response.getRestEmp();
        this.partTimeEmp = response.getPartTimeEmp();
        this.studentNum = response.getStudentNum();
        this.riceCus = response.getRiceCus();
        this.riceVip = response.getRiceVip();
        this.riceEmp = response.getRiceEmp();
        this.numberRice = response.getTotalRiceNum();
    }
}