package com.example.itspower.request;

import lombok.Data;

@Data
public class TransferRequest {
    private int transferId;
    private int groupId;
    private int transferNum;
    private int type;
}
