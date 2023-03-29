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
    private Integer transferNum = 0;
    @Column(name = "report_id")
    private Integer reportId = 0;
    @Column(name = "group_id")
    private Integer groupId = 0;
    @Column(name = "is_access")
    private boolean isAccess;
    @Column(name = "transfer_date")
    private Date transferDate;
    @Column(name = "type")
    @Min(1)
    private Integer type = 0;
    @Transient
    private Integer parentId;
}
