package com.example.itspower.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
}
