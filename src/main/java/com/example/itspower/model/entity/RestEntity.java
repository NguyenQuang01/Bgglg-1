package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "rest")
@Entity
public class RestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String Name;

    @Column(name ="reason_id")
    private Integer reasonId;
    @Column(name ="report_id")
    private Integer reportId;

    public RestEntity(String name, Integer reasonId, Integer reportId) {
        Name = name;
        this.reasonId = reasonId;
        this.reportId = reportId;
    }

    public RestEntity() {
        
    }
}
