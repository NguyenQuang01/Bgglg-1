package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_group")
@Entity
@Data
public class UserGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "group_id")
    private Integer groupId;

}
