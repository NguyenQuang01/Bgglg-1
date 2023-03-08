package com.example.itspower.response.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class UserAulogin {
    @NotNull
    private String userLogin;
    @NotNull
    private String password;
}
