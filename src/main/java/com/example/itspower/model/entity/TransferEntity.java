package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Entity
@Table(name = "transfer")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer transferId;
    @Column(name = "transfer_num")
    private Integer transferNum;
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "is_access")
    private boolean isAccess;
    @Column(name = "transferDate")
    private Date transferDate;
    @Column(name = "type")
    @Min(1)
    private Integer type;
}
