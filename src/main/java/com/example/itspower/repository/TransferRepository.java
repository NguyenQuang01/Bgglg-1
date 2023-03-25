package com.example.itspower.repository;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.request.TransferRequest;
import com.example.itspower.response.transfer.TransferResponseGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;
    @Autowired
    private GroupJpaRepository groupJpaRepository;

    public List<TransferEntity> findByReportId(Integer reportId) {
        List<TransferEntity> entities = transferJpaRepository.findByReportId(reportId);
        return entities;
    }

    public List<TransferEntity> saveTransfer(List<TransferRequest> requests, int reportId) {
        List<TransferEntity> entities = new ArrayList<>();
        for (TransferRequest transfer : requests) {
            TransferEntity entity = new TransferEntity();
            entity.setReportId(reportId);
            entity.setGroupId(transfer.getGroupId());
            entity.setTransferDate(new Date());
            entity.setTransferNum(transfer.getTransferNum());
            entity.setType(transfer.getType());
            entities.add(entity);
        }
        return transferJpaRepository.saveAll(entities);
    }

    public List<TransferEntity> updateTransfer(List<TransferRequest> requests, int reportId) {
        List<TransferEntity> entities = new ArrayList<>();
        for (TransferRequest transfer : requests) {
            TransferEntity entity = new TransferEntity();
            if (transfer.getTransferId() == 0) {
                throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "transferId not exits", HttpStatus.BAD_REQUEST.name());
            }
            entity.setTransferId(transfer.getTransferId());
            entity.setReportId(reportId);
            entity.setGroupId(transfer.getGroupId());
            entity.setTransferDate(new Date());
            entity.setTransferNum(transfer.getTransferNum());
            entity.setType(transfer.getType());
            entities.add(entity);
        }
        return transferJpaRepository.saveAll(entities);
    }

    public List<TransferResponseGroup> findGroupIdAndTransferDate(int groupId) {
        List<TransferEntity> entities = transferJpaRepository.findGroupIdAndTransferDate(groupId, DateUtils.formatDate(new Date()));
        List<TransferResponseGroup> transferResponseGroups = new ArrayList<>();
        for (TransferEntity entity : entities) {
            Optional<GroupEntity> groupEntity = groupJpaRepository.findById(entity.getGroupId());
            transferResponseGroups.add(new TransferResponseGroup(entity.getGroupId(), groupEntity.get().getGroupName(), entity.getTransferNum()));
        }
        return transferResponseGroups;
    }

    public void updateTransferGroup(boolean isAccess, int groupId, String transferDate) {
        transferJpaRepository.updateTransfer(isAccess, groupId, transferDate);
    }

    @Transactional
    @Modifying
    public void deleteTransferReportId(Integer reportId) {
        transferJpaRepository.deleteByReportId(reportId);
    }
}
