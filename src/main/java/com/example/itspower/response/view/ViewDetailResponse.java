package com.example.itspower.response.view;

import lombok.Data;

@Data
public class ViewDetailResponse {
    private String department;

    private Integer student;
    private Integer restEmp;
    //tổng số lao động
    private Integer totalEmp;
    // lao động năng suất
    private Integer laborProductivityTeam;
    private Double ratio;
    //thoi vu to may
    private Integer partTimeEmp;

    private Integer riceVip;
    private Integer riceEmp;
    private Integer riceCus;

    public ViewDetailResponse(String department, Integer totalEmp,Integer laborProductivityTeam) {
        this.department =department;
        this.totalEmp = totalEmp !=null ? totalEmp:0;
        this.laborProductivityTeam =  laborProductivityTeam !=null ? laborProductivityTeam:0;
    }
    public ViewDetailResponse(String department, Integer totalEmp) {
        this.department =department;
        this.totalEmp = totalEmp !=null ? totalEmp:0;

    }
    public ViewDetailResponse(Integer totalEmp, Integer laborProductivityTeam, Integer restEmp, Integer partTimeEmp
           , Integer student, Integer riceCus, Integer riceVip, Integer riceEmp) {
        this.student =student !=null ? student :0;
        this.restEmp = restEmp !=null ? restEmp :0;
        this.totalEmp = totalEmp != null ? totalEmp:0;
        this.laborProductivityTeam = laborProductivityTeam != null ? laborProductivityTeam :0;
        this.partTimeEmp =partTimeEmp !=null ?  partTimeEmp:0;
        this.riceEmp=riceEmp !=null ? riceEmp :0;
        this.riceCus=riceCus !=null  ? riceVip :0;
        this.riceVip=riceVip  !=null ? riceVip:0 ;
    }
}
