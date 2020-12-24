package com.ivan.licenses.service;

import com.ivan.licenses.config.ServiceConfig;
import com.ivan.licenses.model.License;
import com.ivan.licenses.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {
    @Resource
    LicenseRepository licenseRepository;
    @Resource
    ServiceConfig serviceConfig;
    List<License> licenses = new ArrayList<>();

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
        licenseRepository.save(licenses);
    }

    public void updateLicense(License license) {
        licenseRepository.save(licenses);
    }

    public void deleteLicense(String licenseId) {
        licenseRepository.delete(licenseId);
    }
}
