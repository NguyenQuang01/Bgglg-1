package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferJpaRepository extends JpaRepository<TransferEntity, Integer> {
    List<TransferEntity> findByReportId(Integer reportId);
}
