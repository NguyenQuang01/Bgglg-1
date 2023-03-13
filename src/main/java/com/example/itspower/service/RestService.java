package com.example.itspower.service;

import com.example.itspower.model.entity.RestEntity;
import com.example.itspower.response.request.RestRequest;

import java.util.List;

public interface RestService {
    List<RestEntity> save(List<RestRequest> requests, Integer reportId);
}
