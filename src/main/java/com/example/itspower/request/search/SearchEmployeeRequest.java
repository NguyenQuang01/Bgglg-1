package com.example.itspower.request.search;

import lombok.Data;

@Data
public class SearchEmployeeRequest {
    private String employeeName;
    private String groupName;
    private String laborCode;
    private Integer  groupId;
}
