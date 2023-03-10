package com.example.itspower.response.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class UserAulogin {
    @NotBlank
    private String userLogin;
    @NotBlank
    private String password;
}
