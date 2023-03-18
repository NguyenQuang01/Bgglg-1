package com.example.itspower.response.search;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddToUserForm {
    @NotBlank
    private String userLogin;
    @NotBlank
    private String userName;
    @NotNull
    @Size(min = 6, max = 24)
    private String password;
    private boolean isReset=false;
    private boolean isReport=false;
    private boolean isView=false;
}
