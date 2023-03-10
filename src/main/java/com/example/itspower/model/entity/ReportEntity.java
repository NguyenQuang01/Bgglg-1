package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "report")
@Entity
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rest_number")
    private Integer restNumber ;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "user_group_id")
    private Integer userGroupId;
}
