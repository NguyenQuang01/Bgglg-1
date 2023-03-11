package com.example.itspower.response.request;

import com.example.itspower.component.enums.TransferType;
import lombok.Data;

@Data
public class TransferRequest {
    private Integer transferNum;
    private TransferType transferType;
}
