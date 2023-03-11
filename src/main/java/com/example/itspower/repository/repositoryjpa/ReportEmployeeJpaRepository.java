package com.example.itspower.repository.repositoryjpa;
import com.example.itspower.model.entity.ReportDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReportEmployeeJpaRepository extends JpaRepository<ReportDtlEntity, Integer> {
}
