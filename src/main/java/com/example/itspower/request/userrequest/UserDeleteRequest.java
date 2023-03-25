package com.example.itspower.request.userrequest;

import lombok.Data;

import java.util.List;

@Data
public class UserDeleteRequest {
    private String userLogin;
    List<Integer> ids;
}
