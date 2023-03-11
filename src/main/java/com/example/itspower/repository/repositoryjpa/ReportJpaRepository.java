package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ReportJpaRepository extends JpaRepository<ReportEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE report r set  r.rest_number = :#{#restNumber} where r.id =:#{#id} and r.user_group_id = :#{#userGroupId}", nativeQuery = true)
    void reportRestNumber(int restNumber, int id, int userGroupId);

    Optional<ReportEntity> findByIdAndUserGroupId(Integer id, Integer userGroupId);
}
