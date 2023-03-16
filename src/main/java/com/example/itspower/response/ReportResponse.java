package com.example.itspower.response;

import com.example.itspower.model.resultset.ReportDto;
import com.example.itspower.model.resultset.RestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private ReportDto reportDto;
    private List<RestDto> restDtos;
}
