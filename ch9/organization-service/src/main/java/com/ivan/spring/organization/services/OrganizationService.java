package com.ivan.spring.organization.services;

import com.ivan.spring.organization.model.Organization;
import com.ivan.spring.organization.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class OrganizationService {
    @Resource
    private OrganizationRepository orgRepository;
    @Resource
    Tracer tracer;

    public Organization getOrg(String organizationId) {
        Span span = tracer.createSpan("getOrganizationDBCall");
        try {
            return orgRepository.findById(organizationId);
        } catch (Exception e) {
            log.error("error when get organization {} from db", organizationId, e);
        } finally {
            span.tag(Span.SPAN_PEER_SERVICE_TAG_NAME, "mysql");
            span.logEvent(Span.SERVER_SEND);
            tracer.close(span);
        }

        return null;
    }

    public void saveOrg(Organization org) {
        org.setId(UUID.randomUUID().toString());

        orgRepository.save(org);

    }

    public void updateOrg(Organization org) {
        orgRepository.save(org);
    }

    public void deleteOrg(Organization org) {
        orgRepository.delete(org.getId());
    }
}
