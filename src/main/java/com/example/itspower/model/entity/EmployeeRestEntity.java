package com.example.itspower.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee_rest")
public class EmployeeRestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_code")
    private String empCode;
    @Column(name = "reason_id")
    private Integer reasonId;
    @Column(name = "report_id")
    private Integer reportId;
}
