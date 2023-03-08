package com.example.itspower.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_group_role")
@Entity
public class UserGroupRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Column(name = "is_report_role")
    private Boolean isReport;
    @Column(name = "is_edit_role")
    private Boolean isEdit;
    @Column(name = "is_view_role")
    private Boolean isViewReport;
}
