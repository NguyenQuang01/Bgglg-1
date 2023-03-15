package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupJpaRepository extends JpaRepository<UserGroup, Integer> {
}
