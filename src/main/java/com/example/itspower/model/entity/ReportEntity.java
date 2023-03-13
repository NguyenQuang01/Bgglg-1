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

    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "total_productivity")
    private Double totalProductivity;
    @Column(name = "demarcation")
    private Double demarcation;
}
