package com.example.itspower.response.request;

import com.example.itspower.component.util.OrderDateException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReportRequest {
    @NotNull
    private Integer userGroupId;
    @NotNull
    private Double totalProductivity;
    @NotNull
    private Double demarcation;
    @NotBlank
    private String createBy;
    @NotBlank
    @OrderDateException()
    private String orderDate;
    @NotBlank
    @Valid
    ReportDtlRequest reportDtlRequest;
    private List<TransferRequest> transferRequests;
    private List<RestRequest> restRequests;
}
