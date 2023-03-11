package com.example.itspower.response.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReportRequest {
    private int restNum;
    @NotNull
    private String orderDate;
    @Valid
    List<ReportDtlRequest> requests;
}
