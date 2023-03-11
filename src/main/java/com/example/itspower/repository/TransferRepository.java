package com.example.itspower.repository;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.response.TransferResponse;
import com.example.itspower.response.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;

    public TransferResponse reportTransfer(Integer userGroupId, TransferRequest request) {
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setTransferNum(request.getTransferNum());
        transferEntity.setType(request.getTransferType());
        transferEntity = transferJpaRepository.save(transferEntity);
        return new TransferResponse(transferEntity);
    }
}
