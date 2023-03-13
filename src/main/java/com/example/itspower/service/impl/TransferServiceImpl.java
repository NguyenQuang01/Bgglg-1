package com.example.itspower.service.impl;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.repository.TransferRepository;
import com.example.itspower.response.TransferResponse;
import com.example.itspower.response.request.TransferRequest;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<TransferEntity> saveTransfer(List<TransferRequest> transferRequests) {
        List<TransferEntity> entities = transferRepository.saveTransfer(transferRequests);
        return entities;
    }
}
