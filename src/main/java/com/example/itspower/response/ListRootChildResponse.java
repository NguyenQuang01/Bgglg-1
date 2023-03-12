package com.example.itspower.response;
import lombok.Data;
@Data
public class ListRootChildResponse {
    private String groupName;
    private Integer totalEmp;
    //tong lao dong bao nang suat
    private Integer totalProductEmp;
    private Integer totalStudent;
    private Integer totalPartTime;

    private float ratio;

}
