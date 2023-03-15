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
    @Column(name = "transfer_num")
    private Integer transferNum;
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "type")
    private Integer type;
}
