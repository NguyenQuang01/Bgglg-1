package com.example.itspower.response.request;

import lombok.Data;

@Data
public class TransferRequest {
    private Integer reportId;
    private Integer transferNum;

    private Integer userGroupId;

    private Integer transferType;
}
