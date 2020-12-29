package com.ivan.spring.licenses.clients;

import com.ivan.spring.licenses.model.Organization;
import com.ivan.spring.licenses.repository.OrganizationRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
@Slf4j
public class OrganizationRestTemplateClient {
    @Resource
    RestTemplate restTemplate;

    @Resource
    OrganizationRedisRepository organizationRedisRepository;
    @Resource
    Tracer tracer;

    public Organization getOrganization(String orgId) {
        Organization result = queryFromRedis(orgId);
        if (result != null) {
            return result;
        }

        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organizationservice/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, orgId);

        result = restExchange.getBody();
        organizationRedisRepository.save(result);
        return result;
    }

    private Organization queryFromRedis(String orgId) {
        Span span = tracer.createSpan("readOrganizationDataFromRedis");

        try {
            Organization organization = organizationRedisRepository.find(orgId);
            return organization;
        } catch (Exception ex) {
            log.error("error while query organization {} from redis", orgId, ex);
        } finally {
            span.tag(Span.SPAN_PEER_SERVICE_TAG_NAME, "redis");
            span.logEvent(Span.CLIENT_RECV);
            tracer.close(span);
        }

        return null;
    }
}
