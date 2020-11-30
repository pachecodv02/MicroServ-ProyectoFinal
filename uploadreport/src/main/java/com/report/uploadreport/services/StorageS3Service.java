package com.report.uploadreport.services;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StorageS3Service {

    Logger logger = LoggerFactory.getLogger(StorageS3Service.class);

    @Value("${amazon.aws.bucket}")
    private String amazonS3Bucket;

    @Autowired
    AmazonS3 S3Client; 

    public void storageFile (String nameFile, InputStream fileContent){
        S3Client.putObject(new PutObjectRequest(amazonS3Bucket, nameFile, fileContent, new ObjectMetadata()));
    }

    
}
