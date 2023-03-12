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
    @Column(name = "emp_num")
    private Integer empNum;
    @Column(name = "rice_number")
    private Integer riceNumber;
    @Column(name = "num_emp")
    private Integer numEmp;
    @Column(name = "group_id")
    private Integer groupID;
    @Column(name = "part_time_num")
    private Integer partTimeNum;
    @Column(name = "rest_number")
    private Integer restNumber ;
    @Column(name = "student_num")
    private Integer studentNum;
    @Column(name = "transfer_id")
    private Integer transferId;

}
