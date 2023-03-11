package com.example.itspower.service;

import com.example.itspower.response.TransferResponse;
import com.example.itspower.response.request.TransferRequest;

public interface TransferService {
    TransferResponse reportTransfer(Integer userGroupId, TransferRequest request);
}
