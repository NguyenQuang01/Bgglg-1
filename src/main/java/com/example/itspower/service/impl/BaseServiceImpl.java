package com.example.itspower.service.impl;


import com.example.itspower.component.util.ObjectMappingUtils;
import com.example.itspower.dto.EmployeeDto;
import com.example.itspower.entity.BaseEntity;
import com.example.itspower.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
public class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(ID id) {
        T t = repository.findById(id).orElse(null);
        return t == null || t.isDeleted() ? null : t;
    }

    @Override
    @Transactional
    public T creatOrUpdate(ID id, T t) {
        if (id != null) {
            T e = this.getById(id);
            if (e != null) {
                ObjectMappingUtils.copyProperties(t, e, getCurrentUserId(), "id", "createdAt", "createdBy", "isDeleted");
                return repository.save(e);
            } else {
                return null;
            }
        }
        ObjectMappingUtils.copyProperties(t, t, getCurrentUserId(), "id", "createdAt", "createdBy", "isDeleted");
        return repository.save(t);
    }



    @Override
    public boolean deleteById(ID id) {
        try {
            T t = this.getById(id);
            t.setDeleted(true);
            repository.save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getCurrentUserId() {
        EmployeeDto currentUser = null;
        return currentUser == null ? 0L : currentUser.getEmployeeId();
    }
}
