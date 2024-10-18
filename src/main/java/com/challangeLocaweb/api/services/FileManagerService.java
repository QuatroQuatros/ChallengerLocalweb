package com.challangeLocaweb.api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManagerService {

    void uploadFile(String bucketName, String objectKey, MultipartFile file) throws IOException;
    void listObjects(String bucketName);
}
