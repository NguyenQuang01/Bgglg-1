package com.example.itspower.response;

import com.example.itspower.model.entity.RiceEntity;
import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.model.resultset.RestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReportResponse {
    private Integer id;
    private Integer demarcation;
    private Integer laborProductivity;
    private Integer transferNum;
    private Integer supportNum;
    private Integer restNum;
    private Integer partTimeNum;
    private Integer studentNum;
    private Integer totalRice;
    private RiceEntity riceEntity;
    private List<RestDto> restDtos;
    private List<TransferEntity> transfers;

    public ReportResponse(ReportDto reportDt, RiceEntity riceEntity, List<RestDto> restDtos, List<TransferEntity> transfers) {
        this.id = reportDt.getId();
        this.demarcation = reportDt.getDemarcation();
        this.laborProductivity = reportDt.getLaborProductivity();
        this.transferNum = reportDt.getTransferNum();
        this.supportNum = reportDt.getSupportNum();
        this.restNum = reportDt.getRestNum();
        this.partTimeNum = reportDt.getPartTimeNum();
        this.studentNum = reportDt.getStudentNum();
        this.totalRice = reportDt.getTotalRice();
        this.riceEntity = riceEntity;
        this.restDtos = restDtos;
        this.transfers = transfers;
    }

}
