package com.workintech.s18d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(BurgerException burgerException){
        BurgerErrorResponse burgerErrorResponse = new BurgerErrorResponse(burgerException.getMessage());
        return new ResponseEntity<>(burgerErrorResponse,burgerException.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception){
        BurgerErrorResponse response = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
