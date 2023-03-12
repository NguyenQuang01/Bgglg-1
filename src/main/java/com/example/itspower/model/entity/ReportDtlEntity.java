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
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "empNum")
    private Integer empNum;
    @Column(name = "rice_number")
    private Integer riceNumber;
    @Column(name = "numEmp")
    private Integer numEmp;
    @Column(name = "groupID")
    private Integer groupID;
    @Column(name = "part_time_num")
    private Integer partTimeNum;
    @Column(name = "rest_number")
    private Integer restNumber ;
    @Column(name = "student_num")
    private Integer studentNum;
    @Column(name = "transferId")
    private Integer transferId;

}
