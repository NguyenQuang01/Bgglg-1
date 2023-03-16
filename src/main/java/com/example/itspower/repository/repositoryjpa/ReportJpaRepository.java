package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.resultset.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportJpaRepository extends JpaRepository<ReportEntity, Integer>, JpaSpecificationExecutor<ReportEntity> {
    @Query(name = "find_by_report", nativeQuery = true)
    ReportDto findByReport(@Param("reportDate") String reportDate);

    @Query(value = "select * from report r where  DATE_FORMAT(r.report_date, '%Y%m%d') = DATE_FORMAT(:#{#reportDate}, '%Y%m%d') ", nativeQuery = true)
    Optional<ReportEntity> findByReportDate(String reportDate);
}
