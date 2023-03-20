package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.RiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiceJpaRepository extends JpaRepository<RiceEntity, Integer> {
    RiceEntity findByReportId(Integer reportId);
}
