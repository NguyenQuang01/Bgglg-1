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

    public ViewDetailResponse(String department, Integer totalEmp, Double ratio) {
        this.department =department;
        this.totalEmp = totalEmp !=null ? totalEmp:0;
        this.ratio =  ratio !=null ? ratio:0;
    }

    public ViewDetailResponse(Integer totalEmp, Integer laborProductivityTeam, Integer restEmp, Integer partTimeEmp
            , Double ratio, Integer student, Integer riceCus, Integer riceVip, Integer riceEmp) {
        this.student =student !=null ? student :0;
        this.restEmp = restEmp !=null ? restEmp :0;
        this.totalEmp = totalEmp != null ? totalEmp:0;
        this.laborProductivityTeam = laborProductivityTeam != null ? laborProductivityTeam :0;
        this.ratio =ratio !=null ? ratio:0;
        this.partTimeEmp =ratio !=null ?  partTimeEmp:0;
        this.riceEmp=riceEmp !=null ? riceEmp :0;
        this.riceCus=riceCus !=null  ? riceVip :0;
        this.riceVip=riceVip  !=null ? riceVip:0 ;
    }
}
