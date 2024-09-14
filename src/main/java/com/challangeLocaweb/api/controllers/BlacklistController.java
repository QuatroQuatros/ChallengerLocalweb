package com.challangeLocaweb.api.controllers;


import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.services.BlacklistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blacklist")
public class BlacklistController {

    @Autowired
    private MessageSource messageSource;

    private final BlacklistService service;

    @Autowired
    public BlacklistController(BlacklistService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<BlacklistResponseDTO>> listAll(Pageable page){
        String message = messageSource.getMessage("blacklist.search.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.getAll(page)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<BlacklistResponseDTO> getByID(@PathVariable Long id){
        try{
            String message = messageSource.getMessage("blacklist.search.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    service.getById(id)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("blacklist.not.found");
        }

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<BlacklistResponseDTO> store(@RequestBody @Valid BlacklistCreateDTO blacklistData){
        String message = messageSource.getMessage("blacklist.created.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.store(blacklistData)
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<BlacklistResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BlacklistUpdateDTO blacklistData){
            try{
                String message = messageSource.getMessage("blacklist.updated.success", null, LocaleContextHolder.getLocale());
                return new BaseResponseDTO<>(
                        message,
                        service.update(id, blacklistData)
                );
            }catch (ModelNotFoundException e){
                throw new ModelNotFoundException("blacklist.not.found");
            }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            String message = messageSource.getMessage("blacklist.deleted.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, null);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("blacklist.not.found");
        }
    }

}
