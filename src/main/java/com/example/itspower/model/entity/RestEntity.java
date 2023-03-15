package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rest")
@Data
public class RestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rest_name")
    private String restName;
    @Column(name = "reason_id")
    private String reasonId;
    @Column(name = "reportId")
    private String reportId;

}
