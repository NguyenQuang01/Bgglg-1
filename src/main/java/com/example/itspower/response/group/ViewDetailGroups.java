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
    private float laborProductivity;
    private float totalLaborProductivity;
    private Integer numberLeave;
    private Integer partTimeEmp;
    private float ratio;
    private Integer studentNum;
    private Integer totalRiseNum;
    List<ViewDetailGroups> children;

    public ViewDetailGroups viewDetailGroups(Integer numberLeave, float laborProductivity, Integer partTimeEmp, Integer studentNum, Integer totalRiseNum) {
        this.laborProductivity = laborProductivity;
        this.numberLeave = numberLeave;
        this.partTimeEmp = partTimeEmp;
        this.ratio = 0;
        this.studentNum = studentNum;
        this.totalRiseNum = totalRiseNum;
        return this;
    }

    public ViewDetailGroups(ViewDetailGroupResponse response) {
        this.key = response.getGroupKey();
        this.name = response.getName();
        this.parentId = response.getParentId();
        this.enterprise = response.getDemarcation();
        this.office = response.getDemarcation();
        this.laborProductivity = response.getLaborProductivity() == null ? 0 : response.getLaborProductivity();
        this.numberLeave = response.getRestEmp();
        this.partTimeEmp = response.getPartTimeEmp();
        this.studentNum = response.getStudentNum();
        this.totalRiseNum = response.getTotalRiceNum();
    }
}