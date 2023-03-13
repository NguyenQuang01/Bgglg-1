package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "transfer")
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
}
