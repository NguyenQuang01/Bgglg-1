package com.example.itspower.request;

import lombok.Data;

@Data
public class TransferRequest {
    private Integer groupId;
    private Integer transferNum;
    private Integer type;
}
