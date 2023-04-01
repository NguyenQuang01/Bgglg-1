package com.example.itspower.repository;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import com.example.itspower.request.TransferRequest;
import com.example.itspower.response.transfer.TransferResponseGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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

    public Object saveTransfer(List<TransferRequest> requests, Integer reportId) {
        List<TransferEntity> entities = new ArrayList<>();
        for (TransferRequest transfer : requests) {
            Optional<GroupEntity> groupEntity = groupJpaRepository.findByGroupName(transfer.getGroupName());
            TransferEntity entity = new TransferEntity();
            entity.setReportId(reportId);
            entity.setGroupId(groupEntity.get().getId());
            entity.setTransferDate(new Date());
            entity.setTransferNum(transfer.getTransferNum());
            entity.setType(transfer.getType());
            entities.add(entity);
        }
        return transferJpaRepository.saveAll(entities);
    }

    @Transactional
    public Object updateTransfer(List<TransferRequest> requests, Integer reportId) {
        List<TransferEntity> entities = new ArrayList<>();
        for (TransferRequest transfer : requests) {
            Optional<GroupEntity> groupEntity = groupJpaRepository.findByGroupName(transfer.getGroupName());
            TransferEntity entity = new TransferEntity();
            entity.setTransferId(transfer.getTransferId());
            entity.setReportId(reportId);
            entity.setGroupId(groupEntity.get().getId());
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
