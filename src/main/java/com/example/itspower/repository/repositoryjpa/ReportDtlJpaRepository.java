package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.ReportDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDtlJpaRepository extends JpaRepository<ReportDtlEntity, Integer> {

    @Query(value = "UPDATE reportdtl  rd set  rd.emp_name = :#{#empName}, " +
            "rd.emp_num = :#{#empNum}, " +
            "rd.reason_id = :#{#reasonId} where " +
            "rd.report_id = :#{#reportId} and rd.id = :#{#id} ", nativeQuery = true)
    void reportEmpAndReason(String empName, Integer empNum, Integer reasonId, Integer reportId, Integer id);
}
