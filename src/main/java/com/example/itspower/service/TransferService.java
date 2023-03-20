package com.example.itspower.service;

import com.example.itspower.response.transfer.TransferResponseGroup;

import java.util.List;

public interface TransferService {
    List<TransferResponseGroup> findAll();

    void updateTransferGroup(boolean isAccess, int groupId);
}
