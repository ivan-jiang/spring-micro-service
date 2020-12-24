package com.ivan.licenses.clients;

import com.ivan.licenses.model.Organization;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrganizationDiscoveryClient {
    @Resource
    DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");
        if (instances.size() <= 0) {
            return null;
        }

        System.err.println("organizationservice:" + instances.toString());
        // 选择第1个实例进行调用,相当于要自己实现负载均衡
        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        ResponseEntity<Organization> responseEntity = restTemplate.exchange(serviceUri,
                HttpMethod.GET,
                null,
                Organization.class,
                organizationId);

        return responseEntity.getBody();
    }
}
