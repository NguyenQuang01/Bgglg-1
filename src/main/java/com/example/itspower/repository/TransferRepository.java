package com.example.itspower.repository;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.repositoryjpa.TransferJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferRepository {
    @Autowired
    private TransferJpaRepository transferJpaRepository;

    public List<TransferEntity> findByReportId(Integer reportId) {
        List<TransferEntity> entities = transferJpaRepository.findByReportId(reportId);
        return entities;
    }
}
