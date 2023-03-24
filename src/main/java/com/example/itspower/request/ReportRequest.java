package com.example.itspower.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ReportRequest {
    private int id;
    private int demarcation;
    private int restNum;
    private int laborProductivity;
    private int partTimeNum;
    private int studentNum;
    @Valid
    private RiceRequest riceRequests;
    @Valid
    private List<RestRequest> restRequests;
    @Valid
    private List<TransferRequest> transferRequests;
}
