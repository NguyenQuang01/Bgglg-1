package com.example.itspower.model.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "read_report")
    private Boolean readReport;
    @Column(name = "edit_report")
    private Boolean editReport;
    @Column(name = "creat_report")
    private Boolean creatReport;
}
