package com.example.itspower.service.impl;

import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.repository.repositoryjpa.RestRepository;
import com.example.itspower.response.request.RestRequest;
import com.example.itspower.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestServiceImpl implements RestService {
    @Autowired
    private RestRepository repository;

    public List<RestEntity> save(List<RestRequest> requests, Integer reportId) {
        List<RestEntity> entities = new ArrayList<>();
        for (RestRequest item : requests) {
            entities.add(new RestEntity(item.getName(), item.getReasonId(), reportId));
        }
        return repository.saveAll(entities);
    }
}
