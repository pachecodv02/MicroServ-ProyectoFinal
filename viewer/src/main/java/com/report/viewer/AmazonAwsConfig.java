package com.report.viewer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class AmazonAwsConfig {

    @Value("${amazon_aws_accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon_aws_secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon_aws_region}")
    private String amazonAWSRegion;

    @Value("${amazon_dynamodb_endpoint}")
    private String dynamoDbEndpoint;
    
    @Bean
    public AWSCredentials aWSCredentials(){
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new EndpointConfiguration(dynamoDbEndpoint, amazonAWSRegion))
        .withCredentials(new AWSStaticCredentialsProvider(aWSCredentials())).build();
    }


    
}
