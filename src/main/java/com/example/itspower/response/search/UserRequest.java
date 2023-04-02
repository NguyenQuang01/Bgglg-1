package com.example.itspower.response.search;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @NotBlank
    private String userLogin;
    @NotBlank
    private String groupName;
    @NotNull(message = "password > 6 char ")
    @Size(min = 6)
    private String password;
    private boolean isEdit;
    private boolean isView;
    private boolean isReport;
    private boolean isAdmin;
}
