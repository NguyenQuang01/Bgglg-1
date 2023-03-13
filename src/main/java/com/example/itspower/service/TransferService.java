package com.example.itspower.service;

import com.example.itspower.response.TransferResponse;
import com.example.itspower.response.request.TransferRequest;

import java.util.List;

public interface TransferService {
    List<TransferResponse> saveTransfer(List<TransferRequest> transferRequests);
}
