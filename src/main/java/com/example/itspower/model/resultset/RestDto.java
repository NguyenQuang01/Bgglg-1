package com.example.itspower.model.resultset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestDto {
    private int restId;
    private String restName;
    private int reasonId;
    private String reasonName;
}
