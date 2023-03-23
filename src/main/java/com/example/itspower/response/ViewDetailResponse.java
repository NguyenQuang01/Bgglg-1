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

}
