package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.RestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestRepository extends JpaRepository<RestEntity,Integer> {
}
