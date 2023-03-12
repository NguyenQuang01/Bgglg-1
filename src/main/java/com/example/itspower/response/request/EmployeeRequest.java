package com.example.itspower.response.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeRequest {
    @NotNull
    private String empName;
    @NotNull
    private Integer reasonId;
    @NotNull
    private Integer reportId;
}
