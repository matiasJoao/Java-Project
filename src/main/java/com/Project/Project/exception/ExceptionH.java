package com.Project.Project.exception;


import com.Project.Project.dto.ResponseDTO;
import javax.validation.ConstraintViolationException;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionH extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handlerExceptionSQL(SQLIntegrityConstraintViolationException e) {
        ResponseDTO error = new ResponseDTO("400", "CPF OU EMAIL JA CADASTRADOS NO SISTEMA ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handlerExcptionNullPointer(NullPointerException e) {
        ResponseDTO error = new ResponseDTO("400", "INVALID BODY", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handlerExceptionConstrait(ConstraintViolationException e) {
        ResponseDTO error = new ResponseDTO("400", "EMPTY BODY", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity handlerExceptionConnectException(ConnectException e) {
        ResponseDTO error = new ResponseDTO("500", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handlerRunTimeE(RuntimeException e){
        ResponseDTO error = new ResponseDTO("404", "Usuario Nao encontrado", HttpStatus.NOT_FOUND);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity FeingExceptionHandler(FeignException e){
        ResponseDTO error = new ResponseDTO("401", "Token Invalido", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(Forbiden.class)
    public ResponseEntity ForbindHandler(Forbiden e){
        ResponseDTO error = new ResponseDTO("403", "Acesso Invaldio", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity UnauthorizedHandler(Unauthorized e){
        ResponseDTO error = new ResponseDTO("401", "Token Invalido", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
