package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserCreateDTO;
import com.challangeLocaweb.api.dtos.user.UserResponseDTO;
import com.challangeLocaweb.api.dtos.user.UserUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.helpers.AuthHelpers;
import com.challangeLocaweb.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<UserResponseDTO>> listar(Pageable page){
        return new BaseResponseDTO<>(
                "busca de usuários feita com sucesso!",
                service.getAll(page)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserResponseDTO> buscarPorId(@PathVariable Long id){
        try{
            return new BaseResponseDTO<>(
                    "busca de usuário feita com sucesso!",
                    service.getById(id)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("usuário não encontrado");
        }

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<UserResponseDTO> gravar(@RequestBody @Valid UserCreateDTO usuarioDados){
        return new BaseResponseDTO<>(
                "usuário cadastrado com sucesso",
                service.store(usuarioDados)
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<UserResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO usuarioDados){
        AuthHelpers authHelpers = new AuthHelpers();
        if (authHelpers.validateAccess(id)) {
            try{
                return new BaseResponseDTO<>(
                        "usuário atualizado com sucesso!",
                        service.update(id, usuarioDados)
                );
            }catch (ModelNotFoundException e){
                throw new ModelNotFoundException("usuário não encontrado");
            }
        } else {
            throw new AccessDeniedException("Você não tem permissão para atualizar este usuário.");
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> excluir(@PathVariable Long id){
        AuthHelpers authHelpers = new AuthHelpers();
        if (authHelpers.validateAccess(id)) {
            try{
                service.delete(id);
                return new BaseResponseDTO<>("usuário excluido com sucesso", null);
            }catch (ModelNotFoundException e){
                throw new ModelNotFoundException("usuário não encontrado");
            }
        } else {
            throw new AccessDeniedException("Você não tem permissão para excluír este usuário.");
        }

    }
}
