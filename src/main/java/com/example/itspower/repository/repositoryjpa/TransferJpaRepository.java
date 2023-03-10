package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferJpaRepository extends JpaRepository<transfer, Integer> {
}
