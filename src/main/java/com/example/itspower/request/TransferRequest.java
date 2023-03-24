package com.example.itspower.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferRequest {
    @NotNull
    private int groupId;
    private int transferId;
    private int transferNum;
    private int type;
}
