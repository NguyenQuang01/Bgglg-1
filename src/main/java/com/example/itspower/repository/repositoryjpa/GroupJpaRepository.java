package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupJpaRepository extends JpaRepository<GroupEntity, Integer> {

    List<GroupEntity> findAllByParentId(int parentId);
    List<GroupEntity> findAllByParentIdIsNull();
}
