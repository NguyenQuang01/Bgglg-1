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
    @Column(name = "group_Id")
    private Integer groupId;
    @Column(name = "user_id")
    private Integer userId;

}
