package com.example.itspower.filter.entity;

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
}
