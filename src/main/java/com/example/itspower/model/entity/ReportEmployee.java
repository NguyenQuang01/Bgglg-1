package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "report_employee")
@Entity
public class ReportEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "work_date")
    private Date workDate;
    @Column(name = "reason_id")
    private Integer reasonId;
}
