package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.UserGroupRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRoleJpaRepository extends JpaRepository<UserGroupRoleEntity, Integer> {
}
