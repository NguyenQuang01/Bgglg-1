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
    @Column(name = "num_Support")
    private Integer numSupport ;
    @Column(name = "userGroupId")
    private Integer userGroupId;
    @Column(name = "type")
    private Integer type;

}
