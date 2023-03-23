package com.example.itspower.service.impl;

import com.example.itspower.model.entity.ReasonEntity;
import com.example.itspower.repository.ReasonRepository;
import com.example.itspower.request.ReasonRequest;
import com.example.itspower.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReasonServiceImpl implements ReasonService {
    @Autowired
    private ReasonRepository repository;

    @Override
    public List<ReasonEntity> searchALl() {
        return repository.searchALL();
    }

    @Override
    public List<ReasonEntity> save(List<ReasonRequest> reasonRequest) {
        List<ReasonEntity> reasonEntityList = new ArrayList<>();
        reasonRequest.forEach(i-> reasonEntityList.add(new ReasonEntity(i)));
        return repository.save(reasonEntityList);
    }

    @Override
    public ReasonEntity edit(ReasonRequest reasonRequest,int id) {
        if (repository.findById(id).isPresent()){
            ReasonEntity reasonEntity = new ReasonEntity(reasonRequest,id);
            return repository.edit(reasonEntity);
        }else{
            return null;
        }
    }
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

}
