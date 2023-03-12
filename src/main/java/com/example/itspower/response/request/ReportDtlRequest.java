package com.example.itspower.response.request;

import lombok.Data;

@Data
public class ReportDtlRequest {
    private Integer reportId;
    private Integer empNum;
    private Integer riceNumber;
    private Integer numEmp;
    private Integer groupId;
    private Integer partTimeNum;
    private Integer restNumber;
    private Integer studentNum;
    private Integer transferNum;
    private Integer transferSupport;
}
