package com.example.itspower.request;

import com.example.itspower.component.util.GroupNameExcep;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferRequest {
    @NotNull
    @GroupNameExcep()
    private String groupName;
    private int transferId;
    private int transferNum;
    private int type;
}
