package com.example.itspower.response.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class EmployeeRequest {
    @Valid
    List<EmpRequest> requests;
}
