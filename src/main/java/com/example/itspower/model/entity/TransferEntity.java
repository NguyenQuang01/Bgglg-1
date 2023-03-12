package com.example.itspower.model.entity;
import lombok.Data;
import javax.persistence.*;
@Data
@Table(name = "Transfer")
@Entity
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "num_transfer")
    private Integer numTransfer ;
    @Column(name = "num_support")
    private Integer numSupport ;
    @Column(name = "user_group_id")
    private Integer userGroupId;
}
