package com.example.itspower.model.entity;

import com.example.itspower.component.enums.TransferType;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "transfer")
@Entity
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "report_ID")
    private Integer reportId;
    @Column(name = "transfer_num")
    private Integer transferNum;
    @Column(name = "type")
    private TransferType type;
}
