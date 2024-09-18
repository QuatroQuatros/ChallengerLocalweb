package com.challangeLocaweb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challangeLocaweb.api.dtos.BaseResponseDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsCreateDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsResponseDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsUpdateDTO;
import com.challangeLocaweb.api.exceptions.ModelNotFoundException;
import com.challangeLocaweb.api.services.SystemLabelsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/labels")
public class SystemLabelsController {

    @Autowired
    private SystemLabelsService systemLabelsService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDTO<SystemLabelsResponseDTO> createSystemLabels (@RequestBody SystemLabelsCreateDTO systemLabelsCreateDTO) {
        String message = messageSource.getMessage("label.created.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<SystemLabelsResponseDTO>(message, systemLabelsService.store(systemLabelsCreateDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<SystemLabelsResponseDTO> updateSystemLabels (@PathVariable Long id, @RequestBody SystemLabelsUpdateDTO systemLabelsUpdateDTO) {
        try {
            String message = messageSource.getMessage("label.update.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<SystemLabelsResponseDTO>(message, systemLabelsService.update(id, systemLabelsUpdateDTO));
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("label.not.found");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDTO<Object> delete(@PathVariable Long id) {
        try {
            systemLabelsService.delete(id);
            String message = messageSource.getMessage("label.deleted.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(message, null);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("label.not.found");
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<Page<SystemLabelsResponseDTO>> listAll(Pageable page){
        String message = messageSource.getMessage("label.search.success", null, LocaleContextHolder.getLocale());
        return new BaseResponseDTO<>(
                message,
                systemLabelsService.getAll(page)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDTO<SystemLabelsResponseDTO> getByID(@PathVariable Long id){
        try{
            String message = messageSource.getMessage("label.search.success", null, LocaleContextHolder.getLocale());
            return new BaseResponseDTO<>(
                    message,
                    systemLabelsService.getById(id)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("label.not.found");
        }

    }
    
}
