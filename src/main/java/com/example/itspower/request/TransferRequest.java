package com.example.itspower.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferRequest {
    @NotNull
//    @GroupNameExcep()
    private String groupName;
    @NotNull
    private String groupParent;
    private int transferId;
    private int transferNum;
    private int type;
}
