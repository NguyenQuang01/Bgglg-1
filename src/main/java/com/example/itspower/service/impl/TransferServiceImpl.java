package com.example.itspower.service.impl;

import com.example.itspower.repository.TransferRepository;
import com.example.itspower.response.TransferResponse;
import com.example.itspower.response.request.TransferRequest;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public TransferResponse reportTransfer(Integer userGroupId, TransferRequest request) {
        return transferRepository.reportTransfer(userGroupId, request);
    }
}
