package com.example.itspower.response.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCount {
    private int key;
    private String parentName;
    private int totalNum;
}
