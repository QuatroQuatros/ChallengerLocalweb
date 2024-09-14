package com.challangeLocaweb.api.advice;

import com.challangeLocaweb.api.exceptions.DuplicateEntryException;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.exceptions.UserNotAuthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

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
    @ExceptionHandler(DuplicateEntryException.class)
    public Map<String, String> handleDuplicateEntryException(DuplicateEntryException error) {return getErrorMap(error);}


    @ResponseStatus(HttpStatus.CONFLICT)  // 409 Conflict
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegridadeDados(DataIntegrityViolationException error) {
        Throwable rootCause = error.getRootCause();

        if (rootCause instanceof SQLIntegrityConstraintViolationException) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Duplicate entry");
            errorResponse.put("message", rootCause.getMessage());
            errorResponse.put("message2", error.getMessage());
            return errorResponse;
        }

        Map<String, String> genericResponse = new HashMap<>();
        genericResponse.put("error", "Data integrity violation");
        genericResponse.put("message", error.getMessage());
        return genericResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFoundException.class)
    public Map<String, String> handleUserNotFound(ModelNotFoundException error){
        return getErrorMap(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException error){
        return getErrorMap(error);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> handleAccessDenied(AccessDeniedException error){
        return getErrorMap(error);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotAuthenticatedException.class)
    public Map<String, String> handleUserNotAuthenticated(UserNotAuthenticatedException error){return getErrorMap(error);}


    private Map<String, String> getErrorMap(Exception error){
        Map<String, String> errorMap = new HashMap<>();
        String errorMessage = messageSource.getMessage(error.getMessage(), null, LocaleContextHolder.getLocale());
        errorMap.put("error", errorMessage);
        return errorMap;
    }



}

