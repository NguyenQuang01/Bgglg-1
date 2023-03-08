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
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "report_employee_id")
    private Integer reportEmployeeId;
    @Column(name = "report_date")
    private Date report_date;
    @Column(name = "demarcation")
    private Integer demarcation;
    @Column(name = "rest_number")
    private Integer restNumber;
    @Column(name = "part_time_number")
    private Integer partTimeNumber;
    @Column(name = "student_number")
    private Integer studentNumber;
    @Column(name = "transfer")
    private Integer transfer;
    @Column(name = "support")
    private Integer support;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "rice_report")
    private Integer riceReport;
    @Column(name = "total")
    private Integer total;
}
