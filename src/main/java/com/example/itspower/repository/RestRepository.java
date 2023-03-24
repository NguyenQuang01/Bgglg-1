package com.example.itspower.repository;

import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.model.resultset.RestDto;
import com.example.itspower.repository.repositoryjpa.RestJpaRepository;
import com.example.itspower.request.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestRepository {
    @Autowired
    private RestJpaRepository restJpaRepository;

    public List<RestDto> getRests(Integer reportId) {
        return restJpaRepository.findByRest(reportId);
    }


    public List<RestEntity> saveRest(List<RestRequest> requests, Integer reportId) {
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

    @Transactional
    public List<RestEntity> updateRest(List<RestRequest> requests, Integer reportId) {
        List<RestEntity> restEntities = new ArrayList<>();
        for (RestRequest request : requests) {
            RestEntity entity = new RestEntity();
            if (request.getRestId() == 0) {
                throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "restId not exits", HttpStatus.BAD_REQUEST.name());
            }
            entity.setRestId(request.getRestId());
            entity.setRestName(request.getRestName());
            entity.setReasonId(request.getReasonId());
            entity.setReportId(reportId);
            restEntities.add(entity);
        }
        return restJpaRepository.saveAll(restEntities);
    }

    public void deleteRestReportId(int reportId) {
        restJpaRepository.deleteById(reportId);
    }
}
