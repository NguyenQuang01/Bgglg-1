package com.example.itspower.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListUserResponse {

        @JsonProperty("userId")
        private int userId;

        @JsonProperty("userName")
        private String userName;

        @JsonProperty("password")
        private String password;

        @JsonProperty("isView")
        private boolean isView;

        @JsonProperty("isEdit")
        private boolean isEdit;

        @JsonProperty("isReport")
        private boolean isReport;

        @JsonProperty("isAdmin")
        private boolean isAdmin;

        @JsonProperty("groupName")
        private String groupName;

        public ListUserResponse(int userId, String userName, String password, boolean isView, boolean isEdit, boolean isReport, boolean isAdmin, String groupName) {
                this.userId = userId;
                this.userName = userName;
                this.password = password;
                this.isView = isView;
                this.isEdit = isEdit;
                this.isReport = isReport;
                this.isAdmin = isAdmin;
                this.groupName = groupName;
        }
}
