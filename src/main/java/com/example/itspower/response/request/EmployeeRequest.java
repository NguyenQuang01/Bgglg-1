package com.example.itspower.response.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeRequest {
    @NotBlank
    private String empName;
    @NotBlank
    private String empCode;
    @NotNull
    private Integer reasonId;
    @NotNull
    private Integer reportId;
}
