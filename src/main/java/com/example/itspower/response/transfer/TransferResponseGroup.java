package com.example.itspower.response.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseGroup {
    private int transferNum;
    private int groupId;
    private int type;
}
