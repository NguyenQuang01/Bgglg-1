package com.example.itspower.response;

import com.example.itspower.model.entity.ReportDtlEntity;
import lombok.Data;

@Data
public class ReportDtlResponse {
    private int id;
    private int reportId;
    private int empNum;
    private int reasonId;
    private String empName;
    private int partTimeNum;
    private int studentNum;
    private int riceNum;

    public ReportDtlResponse(ReportDtlEntity entity) {
        this.id = entity.getId();
        this.reportId = entity.getReportId();
        this.empNum = entity.getEmpNum();
//        this.reasonId = entity.getReasonId();
//        this.empName = entity.getEmpName();
//        this.partTimeNum = entity.getPartTimeNum();
//        this.studentNum = entity.getStudentNum();
        this.riceNum = entity.getRiceNumber();
    }
}
