package com.example.itspower.response.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeGroupResponse {
    private Integer employeeId;
    private String laborCode;
    private String employeeName;
    private Integer groupId;
    private String groupName;

    public EmployeeGroupResponse(Integer employeeId, String laborCode, String employeeName, Integer groupId, String groupName) {
        this.employeeId = employeeId;
        this.laborCode = laborCode;
        this.employeeName = employeeName;
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
