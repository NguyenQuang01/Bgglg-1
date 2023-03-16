package com.example.itspower.repository;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;

    public List<TransferEntity> findByReportId(Integer reportId) {
        List<TransferEntity> entities = transferJpaRepository.findByReportId(reportId);
        return entities;
    }

    public List<TransferEntity> saveTransfer(List<TransferRequest> requests, Integer reportId) {
        List<TransferEntity> entities = new ArrayList<>();
        for (TransferRequest transfer : requests) {
            TransferEntity entity = new TransferEntity();
            entity.setReportId(reportId);
            entity.setGroupId(transfer.getGroupId());
            entity.setTransferNum(transfer.getTransferNum());
            entity.setType(transfer.getType());
            entities.add(entity);
        }
        return transferJpaRepository.saveAll(entities);
    }
}
