package com.Project.Project.ResponseHandler.Exceptiron;

import com.Project.Project.ResponseHandler.UserValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionH extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handlerException(SQLIntegrityConstraintViolationException e){
        UserValidationResponse error = new UserValidationResponse("400", "CPF OU EMAIL JA CADASTRADOS NO SISTEMA ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
