package com.example.itspower.repository;

import com.example.itspower.model.entity.ReasonEntity;
import com.example.itspower.repository.repositoryjpa.ReasonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ReasonRepository {

    @Autowired
    private ReasonJpaRepository repository;

    public List<ReasonEntity> searchALL() {
        return repository.findAll();
    }

    public List<ReasonEntity> searchById(int id) {
        return repository.findAllById(id);
    }


    public List<ReasonEntity> save(List<ReasonEntity> reasonEntityList) {
        return repository.saveAll(reasonEntityList);
    }

    public ReasonEntity edit(ReasonEntity reasonEntity) {
        return repository.save(reasonEntity);
    }

    public Optional<ReasonEntity> findById(int id){
        return repository.findById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
