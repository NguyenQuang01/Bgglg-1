package com.example.itspower.response.search;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
public class AddToUserForm {
    @NotBlank
    private String userLogin;
    @NotBlank
    private String userName;
    @NotNull
    @Size(min = 6, max = 24)
    private String password;
}
