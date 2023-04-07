package com.example.itspower.model.usertoken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRefreshToken {
    private String headerToken;
    private String newToken;
}
