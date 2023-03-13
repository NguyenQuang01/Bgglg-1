package com.example.itspower.service;

import com.example.itspower.model.entity.TransferEntity;
import com.example.itspower.response.request.TransferRequest;

import java.util.List;

public interface TransferService {
    List<TransferEntity> saveTransfer(List<TransferRequest> transferRequests);
}
