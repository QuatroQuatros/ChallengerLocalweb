package com.challangeLocaweb.api.services;

public interface FileManagerService {

    void uploadFile(String bucketName, String objectKey, String filePath);
    void listObjects(String bucketName);
}
