package com.report.uploadreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UploadreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadreportApplication.class, args);
	}

}
