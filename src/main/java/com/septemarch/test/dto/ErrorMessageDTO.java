package com.septemarch.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String message;
    private HttpStatus status;


}
