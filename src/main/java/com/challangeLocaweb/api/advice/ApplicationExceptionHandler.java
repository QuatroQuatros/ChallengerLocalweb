package com.challangeLocaweb.api.advice;

import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException error){
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = error.getBindingResult().getFieldErrors();
        for (FieldError field : fields){
            errorMap.put(field.getField(), field.getDefaultMessage());
        }
        return  errorMap;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegridadeDados(DataIntegrityViolationException error){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", error.getMessage());

        return  errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFoundException.class)
    public Map<String, String> handleUserNotFound(ModelNotFoundException error){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", error.getMessage());

        return  errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException error){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", error.getMessage());

        return  errorMap;
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> handleAccessDenied(AccessDeniedException error){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", error.getMessage());

        return  errorMap;
    }



}

