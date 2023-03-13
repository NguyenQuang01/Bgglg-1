package com.example.itspower.response;

import com.example.itspower.model.entity.ReportDtlEntity;
import lombok.Data;

@Data
public class ReportDtlResponse {
    private Integer id;
    private Integer reportId;
    private Integer empNum;
    private Integer riceNumber;
    private Integer numEmp;
    private Integer groupID;
    private Integer partTimeNum;
    private Integer restNumber;
    private Integer studentNum;
    private Integer transferId;

    public ReportDtlResponse(ReportDtlEntity entity) {
        this.id = entity.getId();
        this.reportId = entity.getReportId();
        this.empNum = entity.getEmpNum();
        this.riceNumber = entity.getRiceNumber();
        this.numEmp = entity.getNumEmp();
        this.groupID = entity.getGroupID();
        this.partTimeNum = entity.getPartTimeNum();
        this.restNumber = entity.getRestNumber();
        this.studentNum = entity.getStudentNum();
    }
}
