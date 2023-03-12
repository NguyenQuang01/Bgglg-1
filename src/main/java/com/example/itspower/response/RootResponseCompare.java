package com.example.itspower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootResponseCompare {
    RootResponse today;
    RootResponse yesterday;
}
