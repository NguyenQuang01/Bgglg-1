package com.example.itspower.response.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferNumAccept {
    private String groupName;
    private Integer transferNum;
    private Integer id;
}
