package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group_role")
@Data
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_name")
    private String groupName = "";
    @Column(name = "parent_id")
    private Integer parentId = 0;

}
