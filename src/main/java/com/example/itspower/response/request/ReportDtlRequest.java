package com.example.itspower.response.request;

import lombok.Data;

@Data
public class ReportDtlRequest {
    private int empNum;
    private int reasonId;
    private String empName;
    private int partTimeNum;
    private int studentNum;
    private int riceNum;
}
