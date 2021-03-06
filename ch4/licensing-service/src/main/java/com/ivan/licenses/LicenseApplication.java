package com.ivan.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableFeignClients
public class LicenseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenseApplication.class);
    }
}
