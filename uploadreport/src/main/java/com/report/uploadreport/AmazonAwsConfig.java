package com.report.uploadreport;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion para la conexion a AWS S3
 */
@Configuration
public class AmazonAwsConfig {
    
    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAccesKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAwsRegion;


    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3Client.builder()
                .withRegion(amazonAwsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
                .build();
    }

    public AWSCredentials getCredentials(){
        return new BasicAWSCredentials(amazonAwsAccesKey, amazonAwsSecretKey);
    }
}
