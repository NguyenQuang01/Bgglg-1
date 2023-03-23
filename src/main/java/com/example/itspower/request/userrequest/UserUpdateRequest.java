package com.example.itspower.request.userrequest;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {
    @NotBlank
    private String userLogin;
    @NotBlank
    private String groupName;
    private int parentId;
    @NotNull
    @Size(min = 6, max = 24)
    private String password;
    private boolean isEdit;
    private boolean isView;
    private boolean isReport;
    private boolean isAdmin;
}
