package com.example.itspower.model.entity;

import com.example.itspower.component.enums.Type;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "transfer")
@Entity
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Column(name = "transfer_num")
    private Integer transferNum;
    @Column(name = "type")
    private Type type;
}
