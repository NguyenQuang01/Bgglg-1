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
    @Column(name = "reason_id")
    private Integer reasonId;
    @Column(name = "reportId")
    private Integer reportId;
}
