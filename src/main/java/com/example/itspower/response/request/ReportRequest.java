package com.example.itspower.response.request;

import com.example.itspower.component.util.OrderDateException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReportRequest {
    @NotNull
    private Integer userGroupId;
    @NotBlank
    private String createBy;
    @NotBlank
    @OrderDateException()
    private String orderDate;
    @NotBlank
    @Valid
    ReportDtlRequest reportDtlRequest;
}
