package com.example.itspower.model.resultset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private Integer id;
    private Integer demarcation;
    private Integer laborProductivity;
    private Integer transferNum;
    private Integer supportNum;
    private Integer restNum;
    private Integer partTimeNum;
    private Integer studentNum;
    private Integer totalRice;

}
