package com.example.itspower.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Table(name = "Transfer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "num_transfer")
    private Integer numTransfer ;
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Column(name = "transfer_type")
    private Integer transferType ;

    public TransferEntity( Integer numTransfer, Integer userGroupId, Integer transferType,Integer reportId) {
        this.reportId = reportId;
        this.numTransfer = numTransfer;
        this.userGroupId = userGroupId;
        this.transferType = transferType;
    }
}
