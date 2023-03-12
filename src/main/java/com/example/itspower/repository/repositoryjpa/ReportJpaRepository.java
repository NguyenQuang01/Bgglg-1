package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportJpaRepository extends JpaRepository<ReportEntity, Integer> {
    @Query(value = "select * from report r where DATE_FORMAT(r.order_date , '%Y%m%d') =  DATE_FORMAT(:#{#orderDate} , '%Y%m%d')", nativeQuery = true)
    Optional<ReportEntity> findByOrderDate(String orderDate);

    Optional<ReportEntity> findByIdAndUserGroupId(Integer id, Integer userGroupId);


}
