package com.example.itspower.component.enums;

public enum ERoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_NHAN_VIEN("ROLE_NHAN_VIEN");

    private String value;
    public String GetValue(){
        return value;
    }
    ERoles(String value) {
        this.value = value;
    }
}
