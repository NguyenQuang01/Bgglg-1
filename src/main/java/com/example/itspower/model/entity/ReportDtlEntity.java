package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "reportdtl")
@Entity
public class ReportDtlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rest_id")
    private Integer reportId;
    @Column(name = "empNum")
    private Integer empNum;
    @Column(name = "part_time_num")
    private Integer partTimeNum;
    @Column(name = "student_num")
    private Integer studentNum;
    @Column(name = "rice_number")
    private Integer riceNumber;
}
