package com.example.springbootexceptions.exception.config;

import com.example.springbootexceptions.dto.ErrorDTO;
import com.example.springbootexceptions.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ErrorDTO.builder().message(ex.getMessage()).build());
    }
}