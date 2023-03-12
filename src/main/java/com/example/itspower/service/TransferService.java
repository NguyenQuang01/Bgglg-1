package com.example.itspower.service;

import com.example.itspower.model.entity.TransferEntity;

public interface TransferService {
    TransferEntity saveTransfer(Integer numSp, Integer numTransfer, Integer reportId, Integer userGroupId);
}
