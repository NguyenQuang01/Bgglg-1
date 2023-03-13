package com.example.itspower.service.impl;

import com.example.itspower.model.entity.ReasonEntity;
import com.example.itspower.repository.ReasonRepository;
import com.example.itspower.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonServiceImpl implements ReasonService {
    @Autowired
    private ReasonRepository reasonRepository;

    @Override
    public List<ReasonEntity> getReason() {
        return reasonRepository.getReason();
    }
}
