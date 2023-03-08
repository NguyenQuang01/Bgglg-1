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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
}
