package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.emails.EmailCreateDTO;
import com.challangeLocaweb.api.dtos.emails.EmailResponseDTO;
import com.challangeLocaweb.api.dtos.emails.EmailUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private MessageSource messageSource;

    private final EmailService service;

    @Autowired
    public EmailController(EmailService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<EmailResponseDTO>> listAll(Pageable page){
        String message = messageSource.getMessage("email.search.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.getAll(page)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<EmailResponseDTO> getByID(@PathVariable Long id){
        try{
            String message = messageSource.getMessage("email.search.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    service.getById(id)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("email.not.found");
        }

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<EmailResponseDTO> store(@RequestBody @Valid EmailCreateDTO emailData){
        String message = messageSource.getMessage("email.created.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                service.store(emailData)
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<EmailResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EmailUpdateDTO emailData){
        try{
            String message = messageSource.getMessage("email.updated.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    service.update(id, emailData)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("email.not.found");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            String message = messageSource.getMessage("email.deleted.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, null);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("email.not.found");
        }
    }
}
