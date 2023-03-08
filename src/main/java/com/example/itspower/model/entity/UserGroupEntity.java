package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_group")
@Data
public class UserGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "office")
    private String office;
    @Column(name = "enterprise")
    private String enterprise;
    @Column(name = "odd_unit")
    private String oddUnit;
}
