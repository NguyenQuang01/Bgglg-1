package com.example.itspower.response.request;

import com.example.itspower.component.enums.TransferType;
import lombok.Data;
import lombok.NonNull;

@Data
public class TransferRequest {
    private Integer transferNum;
    @NonNull
    private Integer userGroupId;
    @NonNull
    private Integer reportId;
    @NonNull
    private TransferType transferType;
}
