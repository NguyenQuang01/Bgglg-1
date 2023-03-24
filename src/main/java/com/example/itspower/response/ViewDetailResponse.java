package com.example.itspower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class ViewDetailResponse {
//    private String department;
    private Integer student;
    private Integer restEmp;
    //tổng số lao động
    private Integer totalEmp;
    // lao động năng suất
    private Integer laborProductivityTeam;
    private Double ratio;
    //thoi vu to may
    private Integer partTimeEmp;

    public ViewDetailResponse(Integer student, Integer restEmp, Integer totalEmp,
                              Integer laborProductivityTeam, Double ratio, Integer partTimeEmp) {
//        this.department = department;
        this.student = student;
        this.restEmp = restEmp;
        this.totalEmp = totalEmp;
        this.laborProductivityTeam = laborProductivityTeam;
        this.ratio = ratio;
        this.partTimeEmp = partTimeEmp;
    }
}
