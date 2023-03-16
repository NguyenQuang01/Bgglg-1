package com.example.itspower.model.resultset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestDto {
    private Integer restId;
    private String restName;
    private String reasonName;
}
