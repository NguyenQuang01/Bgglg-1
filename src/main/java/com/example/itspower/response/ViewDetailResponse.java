package com.example.itspower.response;

import lombok.Data;

@Data
public class ViewDetailResponse {
    private String department;

    private Integer student=0;
    private Integer restEmp=0;
    //tổng số lao động
    private Integer totalEmp=0;
    // lao động năng suất
    private Integer laborProductivityTeam=0;
    private Double ratio=0.0;
    //thoi vu to may
    private Integer partTimeEmp=0;

    private Integer riceVip=0;
    private Integer riceEmp=0;
    private Integer riceCus=0;


    public ViewDetailResponse( Integer totalEmp,Integer laborProductivityTeam,Integer restEmp,Integer partTimeEmp
            ,  Double ratio,Integer student,Integer riceCus,Integer riceVip,Integer riceEmp) {
        this.student = student;
        this.restEmp = restEmp;
        this.totalEmp = totalEmp;
        this.laborProductivityTeam = laborProductivityTeam;
        this.ratio = ratio;
        this.partTimeEmp = partTimeEmp;
        this.riceEmp=riceEmp;
        this.riceCus=riceCus;
        this.riceVip=riceVip;
    }
}
