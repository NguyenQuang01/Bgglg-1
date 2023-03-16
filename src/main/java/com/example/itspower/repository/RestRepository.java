package com.example.itspower.repository;

import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.repositoryjpa.RestJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestRepository {
    @Autowired
    private RestJpaRepository restJpaRepository;

    public List<RestDto> getRests(Integer reportId) {
        return restJpaRepository.findByRest(reportId);
    }
}
