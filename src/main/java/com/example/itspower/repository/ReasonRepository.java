package com.example.itspower.repository;

import com.example.itspower.model.entity.ReasonEntity;
import com.example.itspower.repository.repositoryjpa.ReasonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReasonRepository {
    @Autowired
    private ReasonJpaRepository reasonJpaRepository;

    public List<ReasonEntity> getReason() {
        return reasonJpaRepository.findAll();
    }
}
