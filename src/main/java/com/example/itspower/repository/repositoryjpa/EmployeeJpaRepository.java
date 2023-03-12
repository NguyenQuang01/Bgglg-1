package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.EmployeeRestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeRestEntity, Integer> {
}
