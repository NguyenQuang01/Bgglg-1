package com.example.itspower.repository;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.response.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;

    public List<TransferEntity> saveTransfer(List<TransferRequest> transferRequests) {
        List<TransferEntity> transferEntities = new ArrayList<>();
        for (TransferRequest item : transferRequests) {
            TransferEntity transferEntity = new TransferEntity();
            transferEntity.setUserGroupId(item.getUserGroupId());
            transferEntity.setNumTransfer(item.getTransferNum());
            transferEntity.setReportId(item.getReportId());
            transferEntity.setTransferType(item.getTransferType());
            transferEntities.add(transferEntity);
        }
        return transferJpaRepository.saveAll(transferEntities);
    }
}
