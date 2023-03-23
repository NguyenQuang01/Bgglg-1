package com.example.itspower.model.entity;

import com.example.itspower.request.ReasonRequest;
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
    private Integer id;
    @Column(name = "name")
    private String name;
    public ReasonEntity(ReasonRequest reasonRequest){
        this.name= reasonRequest.getName();
    }
    public ReasonEntity(ReasonRequest reasonRequest,int id){
        this.id=id;
        this.name= reasonRequest.getName();
    }
}
