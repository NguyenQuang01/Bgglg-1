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
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "user_id")
    private String userId;

}
