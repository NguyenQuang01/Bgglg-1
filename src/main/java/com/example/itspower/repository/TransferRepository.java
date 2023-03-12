package com.example.itspower.repository;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;

    public TransferEntity saveTransfer(Integer numSp, Integer numTransfer, Integer reportId, Integer userGroupId) {
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setUserGroupId(userGroupId);
        transferEntity.setNumTransfer(numTransfer);
        transferEntity.setNumSupport(numSp);
        transferEntity.setReportId(reportId);
        return transferJpaRepository.save(transferEntity);
    }
}
