package com.example.itspower.model.entity;
import com.example.itspower.component.enums.TransferType;
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
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Column(name = "transferType")
    private TransferType transferType ;
}
