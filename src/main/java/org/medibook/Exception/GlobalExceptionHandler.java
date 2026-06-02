package org.medibook.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException e){

        ErrorMessage message=new ErrorMessage(404, HttpStatus.BAD_REQUEST,e.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>methodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String,Object>errorMessage=new HashMap<>();

        List<FieldError>errorList=e.getBindingResult().getFieldErrors();

        for (FieldError error: errorList){

            String field=error.getField();

            String message=error.getDefaultMessage();

            errorMessage.put(field,message);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage>badRequestException(BadRequestException e){

        ErrorMessage errorMessage=new ErrorMessage(400,HttpStatus.BAD_REQUEST,e.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
