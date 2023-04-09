package com.example.itspower.model.usertoken;

import com.example.itspower.model.resultset.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRefreshToken {
    private String headerToken;

    private String newToken;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    private int userId;
    @JsonProperty("isEdit")
    private boolean isEdit;
    @JsonProperty("isReport")
    private boolean isReport;
    @JsonProperty("isView")
    private boolean isView;

    private int groupId;

    private String groupName;

    private String userLogin;

    private boolean isCheckReport;

    public UserRefreshToken (String bearer, String newToken, UserDto loginInfor,String userLogin, boolean isCheckReport){
        this.headerToken= bearer;
        this. newToken= newToken;
        this. isAdmin= loginInfor.isAdmin();
        this. userId= loginInfor.getUserId();
        this. isEdit= loginInfor.isEdit();
        this. isReport= loginInfor.isReport();
        this. isView= loginInfor.isView();
        this. groupId= loginInfor.getGroupId();
        this. groupName= loginInfor.getGroupName();
        this. userLogin=userLogin;
        this. isCheckReport=isCheckReport;
    }

}
