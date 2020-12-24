package com.ivan.licenses.service;

import com.ivan.licenses.clients.OrganizationDiscoveryClient;
import com.ivan.licenses.clients.OrganizationFeignClient;
import com.ivan.licenses.config.ServiceConfig;
import com.ivan.licenses.model.License;
import com.ivan.licenses.model.Organization;
import com.ivan.licenses.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {
    @Resource
    LicenseRepository licenseRepository;
    @Resource
    ServiceConfig serviceConfig;
    @Resource
    OrganizationDiscoveryClient organizationDiscoveryClient;
    @Resource
    OrganizationFeignClient organizationFeignClient;

    public List<License> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        license.setComment(serviceConfig.getProperty());
        return license;
    }

    public void saveLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(String licenseId) {
        licenseRepository.delete(licenseId);
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        license.setOrganizationId(organizationId);
        license.setOrganizationName(org.getName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setContactName(org.getContactName());
        license.setComment(serviceConfig.getProperty());

        return license;
    }

    private Organization retrieveOrgInfo(String organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationFeignClient.getOrganization(organizationId);
        }

        return organization;
    }
}
