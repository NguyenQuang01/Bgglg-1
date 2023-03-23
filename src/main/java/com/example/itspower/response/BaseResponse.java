package com.example.itspower.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer code;

    private String message;

    private T data;
}
