package com.example.itspower.response.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class TransferRequest {
    private Integer transferNum;
    @NonNull
    private Integer userGroupId;
    @NonNull
    private Integer transferType;
}
