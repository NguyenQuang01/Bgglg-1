package com.example.itspower.model.resultset;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDto {
    private Integer demarcation;
    private Integer restNum;
    private Integer laborProductivity;
    private Integer transferNum;
    private Integer supportNum;
    private String restName;
    private String seasonName;
    private Integer partTimeNum;
    private Integer studentNum;
    private Integer totalRice;

    public ReportDto(Integer demarcation, Integer restNum, Integer laborProductivity, Integer transferNum, Integer supportNum, String restName, String seasonName, Integer partTimeNum, Integer studentNum, Integer totalRice) {
        this.demarcation = demarcation;
        this.restNum = restNum;
        this.laborProductivity = laborProductivity;
        this.transferNum = transferNum;
        this.supportNum = supportNum;
        this.restName = restName;
        this.seasonName = seasonName;
        this.partTimeNum = partTimeNum;
        this.studentNum = studentNum;
        this.totalRice = totalRice;
    }
}
