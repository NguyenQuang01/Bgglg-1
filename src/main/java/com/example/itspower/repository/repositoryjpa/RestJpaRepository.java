package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.model.resultset.RestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RestJpaRepository extends JpaRepository<RestEntity, Integer>, JpaSpecificationExecutor<RestEntity> {
    @Query(name = "find_by_rest", nativeQuery = true)
    List<RestDto> findByRest(@Param("reportId") Integer reportId);

    @Transactional
    @Modifying
    void deleteByReportId(Integer reportId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from rest where id in(:#{#ids}) ")
    void deleteRestIds(List<Integer> ids);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from rest where id in:ids and report_id =:reportId ")
    void deleteRestIdsAndReportId(@Param("ids") List<Integer> ids, @Param("reportId") Integer reportId);

    @Query(value = "select NULLIF(COUNT(r.report_id),0) as countReport from rest r where report_id =:reportId", nativeQuery = true)
    int findByCount(@Param("reportId") Integer reportId);
}
