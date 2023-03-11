package com.example.itspower.response;

import com.example.itspower.component.enums.TransferType;
import com.example.itspower.model.entity.TransferEntity;
import lombok.Data;

@Data
public class TransferResponse {
    private Integer id;
    private Integer transferNum;
    private TransferType transferType;

    public TransferResponse(TransferEntity entity) {
        this.id = entity.getId();
        this.transferNum = entity.getTransferNum();
        this.transferType = entity.getType();
    }
}
