package com.example.itspower.response;

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

    public ViewDetailResponse( Integer totalEmp,Integer laborProductivityTeam,Integer restEmp,Integer partTimeEmp,  Double ratio,Integer student) {
        this.student = student;
        this.restEmp = restEmp;
        this.totalEmp = totalEmp;
        this.laborProductivityTeam = laborProductivityTeam;
        this.ratio = ratio;
        this.partTimeEmp = partTimeEmp;
    }
}
