package com.ivan.spring.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 测试 http://localhost:5555/api/organization/v1/organizations/442adb6e-fa58-47f3-9ca2-ed1fecdfe86c
 * http://localhost:5555/api/licensing/v1/organizations/442adb6e-fa58-47f3-9ca2-ed1fecdfe86c/licenses/08dbe05-606e-4dad-9d33-90ef10e334f9
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        return template;
    }

    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
