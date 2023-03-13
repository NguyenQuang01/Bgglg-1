package com.example.itspower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailResponse {
    private Number demarcation;
    private Integer restNum;
    private Integer partTimeNum;
    private Integer studentNum;
    private Integer transferNum;
    private Integer supportNum;
    private Integer riceNum;
    private Number totalProductivity;

}
