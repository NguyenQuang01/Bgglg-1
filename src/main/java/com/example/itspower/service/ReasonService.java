package com.example.itspower.service;

import com.example.itspower.model.entity.ReasonEntity;
import com.example.itspower.request.ReasonRequest;

import java.util.List;


public interface ReasonService {
    List<ReasonEntity> searchALl();
    List<ReasonEntity> searchById(int id);

    List<ReasonEntity> save(List<ReasonRequest> reasonRequest);

    ReasonEntity edit(ReasonRequest reasonRequest,int id);

    void deleteAll();

    void deleteById(int id);
}
