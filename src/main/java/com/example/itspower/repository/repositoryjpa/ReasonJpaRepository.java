package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonJpaRepository extends JpaRepository<ReasonEntity, Integer> {
}
