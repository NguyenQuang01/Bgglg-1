package com.example.itspower.response.search;

import lombok.Data;

@Data
public class AddToUserForm {
//    @NotBlank
    private String userLogin;
//    @NotBlank
    private String userName;
//    @NotNull
//    @Size(min = 6, max = 24)
    private String password;
}
