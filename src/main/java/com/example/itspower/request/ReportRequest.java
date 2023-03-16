package com.example.itspower.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReportRequest {
    private Integer demarcation;
    private Integer restNum;
    private Integer laborProductivity;
    private Integer partTimeNum;
    private Integer studentNum;
    @NotNull
    private Integer groupId;
    private RiceRequest riceRequests;
    private List<RestRequest> restRequests;
    private List<TransferRequest> transferRequests;
}
