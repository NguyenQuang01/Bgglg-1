package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.model.resultset.RestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestJpaRepository extends JpaRepository<RestEntity, Integer>, JpaSpecificationExecutor<RestEntity> {
    @Query(name = "find_by_rest", nativeQuery = true)
    List<RestDto> findByRest(@Param("reportId") Integer reportId);
}
