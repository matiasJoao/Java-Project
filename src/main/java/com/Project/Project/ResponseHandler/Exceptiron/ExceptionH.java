package com.Project.Project.ResponseHandler.Exceptiron;


import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import javax.validation.ConstraintViolationException;
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
        ResponseJSONhandler error = new ResponseJSONhandler("400", "CPF OU EMAIL JA CADASTRADOS NO SISTEMA ", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handlerExcptionNullPointer(NullPointerException e) {
        ResponseJSONhandler error = new ResponseJSONhandler("400", "INVALID BODY", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handlerExceptionConstrait(ConstraintViolationException e) {
        ResponseJSONhandler error = new ResponseJSONhandler("400", "EMPTY BODY", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity handlerExceptionConnectException(ConnectException e) {
        ResponseJSONhandler error = new ResponseJSONhandler("500", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handlerRunTimeE(RuntimeException e){
        ResponseJSONhandler error = new ResponseJSONhandler("400", e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
