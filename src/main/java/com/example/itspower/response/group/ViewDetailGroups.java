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
    private Float office;
    private Float enterprise;
    private Float laborProductivity;
    private Float totalLaborProductivity;
    private Integer numberLeave;
    private Integer partTimeEmp;
    private Float ratio;
    private Integer studentNum;
    private Float totalRatioOfOfficeAndDonvile;
    private Integer totalRiceCus;
    private Integer totalRiceVip;
    private Integer totalRiceEmp;
    private Integer riceCus;
    private Integer riceVip;
    private Integer riceEmp;
    List<ViewDetailGroups> children;

    public ViewDetailGroups viewDetailGroups(Integer numberLeave, float laborProductivity, Integer partTimeEmp, Integer studentNum, Integer totalRiceCus, Integer totalRiceVip, Integer totalRiceEmp) {
        this.laborProductivity = laborProductivity;
        this.numberLeave = numberLeave;
        this.partTimeEmp = partTimeEmp;
        this.studentNum = studentNum;
        this.totalRiceVip = totalRiceVip;
        this.totalRiceCus = totalRiceCus;
        this.totalRiceEmp = totalRiceEmp;
        return this;
    }

    public ViewDetailGroups(ViewDetailGroupResponse response) {
        this.key = response.getGroupKey();
        this.name = response.getName();
        this.parentId = response.getParentId();
        this.enterprise = Float.valueOf(response.getDemarcation());
        this.office = Float.valueOf(response.getDemarcation());
        this.laborProductivity = Float.valueOf(response.getLaborProductivity());
        this.numberLeave = response.getRestEmp();
        this.partTimeEmp = response.getPartTimeEmp();
        this.studentNum = response.getStudentNum();
        this.totalRiceVip = response.getRiceCus();
        this.totalRiceEmp = response.getRiceEmp();
        this.totalRiceCus = response.getRiceCus();
        this.riceCus = response.getRiceCus();
        this.riceVip = response.getRiceVip();
        this.riceEmp = response.getRiceEmp();
    }
}