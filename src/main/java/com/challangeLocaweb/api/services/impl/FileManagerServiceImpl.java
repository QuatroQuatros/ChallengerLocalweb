package com.challangeLocaweb.api.services.impl;

import com.challangeLocaweb.api.services.FileManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;

import java.net.URI;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileManagerServiceImpl implements FileManagerService {

    private final S3Client s3Client;

    public FileManagerServiceImpl(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                                  @Value("${cloud.aws.credentials.secret-key}") String secretKey,
                                  @Value("${cloud.aws.s3.endpoint}") String endpoint) {

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        this.s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .endpointOverride(URI.create(endpoint))  // MinIO endpoint
                .region(Region.US_EAST_1)  // Define a região
                .build();
    }

    @Override
    public void uploadFile(String bucketName, String objectKey, MultipartFile file) throws IOException {
        // Converte o MultipartFile em um arquivo temporário para upload
        java.nio.file.Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        // Faz o upload do arquivo temporário para o MinIO (S3 Simulado)
        s3Client.putObject(putObjectRequest, tempFile);

        // Deleta o arquivo temporário após o upload
        Files.delete(tempFile);
        System.out.println("Upload completo: " + objectKey);
    }

    @Override
    public void listObjects(String bucketName) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        for (S3Object object : response.contents()) {
            System.out.println(object.key());
        }
    }
}

