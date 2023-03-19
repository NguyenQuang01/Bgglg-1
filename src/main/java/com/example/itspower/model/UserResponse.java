package com.example.itspower.model;

import com.example.itspower.model.resultset.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    @JsonProperty("isEdit")
    private boolean isEdit;
    @JsonProperty("isReport")
    private boolean isReport;
    @JsonProperty("isView")
    private boolean isView;
    @JsonProperty("groupId")
    private int groupId;
    private String token;
    private String groupName;
    private String userLogin;

    public UserResponse(String userLogin, UserDto dto, String token) {
        this.userLogin = userLogin;
        this.isAdmin = dto.isAdmin();
        this.isEdit = dto.isEdit();
        this.isReport = dto.isReport();
        this.isView = dto.isView();
        this.groupId = dto.getGroupId();
        this.token = token;
        this.groupName = dto.getGroupName();
    }
}
