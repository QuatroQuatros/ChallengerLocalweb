package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class TestController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Object> hello(){
        return new BaseResponseDTO<>("batata");
    }

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Object> helloAuth(){
        return new BaseResponseDTO<>("batata autenticada");
    }
}
