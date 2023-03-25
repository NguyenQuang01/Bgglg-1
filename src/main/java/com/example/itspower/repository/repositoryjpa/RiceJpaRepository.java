package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.RiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RiceJpaRepository extends JpaRepository<RiceEntity, Integer> {
    RiceEntity findByReportId(Integer reportId);
    @Transactional
    @Modifying
    void deleteByReportId(Integer reportId);
}
