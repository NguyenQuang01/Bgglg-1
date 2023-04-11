package com.example.itspower.request;

import lombok.Data;

@Data
public class TransferRequest {

    private Integer groupId ;
    private int transferId;
    private int transferNum;
    private int type;
}
