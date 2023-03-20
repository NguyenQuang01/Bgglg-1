package com.example.itspower.request;

import lombok.Data;

import java.util.List;

@Data
public class ReportRequest {
    private int id;
    private int demarcation;
    private int restNum;
    private int laborProductivity;
    private int partTimeNum;
    private int studentNum;
    private RiceRequest riceRequests;
    private List<RestRequest> restRequests;
    private List<TransferRequest> transferRequests;
}
