package com.example.itspower.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reason")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReasonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    @Column(name = "name")
    private String name = "";
}
