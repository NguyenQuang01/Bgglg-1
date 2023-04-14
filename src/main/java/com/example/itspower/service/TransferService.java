package com.example.itspower.service;

import com.example.itspower.response.transfer.TransferResponseGroup;

import java.text.ParseException;
import java.util.List;

public interface TransferService {
    List<TransferResponseGroup> findGroupIdAndTransferDate(int groupId,String reportDate) throws ParseException;

    void updateTransferGroup(boolean isAccess, int groupId, String transferDate);
}
