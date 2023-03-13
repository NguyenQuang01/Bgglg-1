package com.example.itspower.response;

import com.example.itspower.model.entity.TransferEntity;
import lombok.Data;

@Data
public class TransferResponse {
    private Integer transferNum;
    private Integer userGroupId;
    private Integer reportId;
    private Integer transferType;

    public TransferResponse(TransferEntity transferEntity) {
        this.transferNum = transferEntity.getNumTransfer();
        this.userGroupId = transferEntity.getUserGroupId();
        this.reportId = transferEntity.getReportId();
        this.transferType = transferEntity.getTransferType();
    }
}
