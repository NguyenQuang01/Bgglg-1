package com.example.itspower.response.request;

import lombok.Data;

@Data
public class ReportEmpNumRequest {
    private Integer id;
    private Integer reportId;
    private String empName;
    private Integer empNum;
    private Integer reasonId;
}
