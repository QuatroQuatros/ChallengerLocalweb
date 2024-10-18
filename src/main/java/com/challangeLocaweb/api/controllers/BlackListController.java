package com.challangeLocaweb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.challangeLocaweb.api.services.BlackListService;

import jakarta.validation.Valid;

import com.challangeLocaweb.api.dtos.blacklist.BlackListCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/blacklist")
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<BlackListResponseDTO>> listar(Pageable page){
        return new BaseResponseDTO<>("Lista feita com sucesso!", blackListService.getAll(page));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<BlackListResponseDTO> buscarPorId(@PathVariable Long id){
        try{
            return new BaseResponseDTO<>("Busca feita com sucesso!", blackListService.getById(id));
        } catch (ModelNotFoundException e){
            throw new ModelNotFoundException("BlackList não encontrada");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<BlackListResponseDTO> gravar(@RequestBody @Valid BlackListCreateDTO blackListCreateDTO) {
        return new BaseResponseDTO<>("Entidade cadastrada com sucesso", blackListService.store(blackListCreateDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<BlackListResponseDTO> atualizar(@PathVariable Long id, @RequestBody BlackListUpdateDTO blackListUpdateDTO){
        try{
            return new BaseResponseDTO<>("Atualização feita com sucesso!", blackListService.update(id, blackListUpdateDTO)); 
        } catch (ModelNotFoundException e){
            throw new ModelNotFoundException("BlackList não encontrada");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> excluir(@PathVariable Long id){
        try {
            blackListService.delete(id);
            return new BaseResponseDTO<>("Entidade removida com sucesso!");
        } catch (ModelNotFoundException e){
            throw new ModelNotFoundException("BlackList não encontrada");
        }
    }
}
