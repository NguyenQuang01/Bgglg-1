package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity, Integer> {
}
