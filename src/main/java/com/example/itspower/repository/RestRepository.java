package com.example.itspower.repository;

import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.repositoryjpa.RestJpaRepository;
import com.example.itspower.request.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestRepository {
    @Autowired
    private RestJpaRepository restJpaRepository;

    public List<RestDto> getRests(Integer reportId) {
        return restJpaRepository.findByRest(reportId);
    }

    public List<RestEntity> saveRest(List<RestRequest> requests,Integer reportId) {
        List<RestEntity> restEntities = new ArrayList<>();
        for (RestRequest request : requests) {
            RestEntity entity = new RestEntity();
            entity.setRestName(request.getRestName());
            entity.setReasonId(request.getReasonId());
            entity.setReportId(reportId);
            restEntities.add(entity);
        }
        return restJpaRepository.saveAll(restEntities);
    }
}
