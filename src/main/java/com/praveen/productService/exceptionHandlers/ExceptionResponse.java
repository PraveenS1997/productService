package com.praveen.productService.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private int statusCode;
}
