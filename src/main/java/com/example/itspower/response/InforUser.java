package com.example.itspower.response;

import lombok.Data;

@Data
public class InforUser {
    private String userName;
    private Boolean isAdmin;
    private Boolean readReport;
    private Boolean updateReport;
    private Boolean createReport;
    private Integer groupId;
    private String groupName;
    private Boolean reported;
    private String token;

}
