package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/teste")
public class TestController {

    @Autowired
    private MessageSource messageSource;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Object> hello() {
        String message = messageSource.getMessage("batata", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(message);
    }

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Object> helloAuth(){
        return new BaseResponseDTO<>("batata autenticada");
    }

}
