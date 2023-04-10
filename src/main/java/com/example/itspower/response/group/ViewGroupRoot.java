package com.example.itspower.response.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewGroupRoot {
    private int id;
    private String groupName;
    private int numberChild;
}
