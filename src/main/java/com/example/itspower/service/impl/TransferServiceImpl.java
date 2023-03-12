package com.example.itspower.service.impl;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.TransferRepository;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public TransferEntity saveTransfer(Integer numSp, Integer numTransfer, Integer reportId, Integer userGroupId) {
        return null;
    }
}
