package com.challangeLocaweb.api.controllers;

import com.challangeLocaweb.api.services.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileManagerController {

    private final FileManagerService fileManagerService;

    @Autowired
    public FileManagerController(FileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo está vazio");
        }

        try {
            // Extrair o nome do arquivo e enviá-lo para o bucket
            String objectKey = file.getOriginalFilename();
            fileManagerService.uploadFile("email-files", objectKey, file);
            return ResponseEntity.ok("Upload do arquivo " + objectKey + " realizado com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload do arquivo: " + e.getMessage());
        }
    }
}
