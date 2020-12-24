package com.ivan.licenses.controller;

import com.ivan.licenses.config.ServiceConfig;
import com.ivan.licenses.model.License;
import com.ivan.licenses.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseController {
    @Resource
    LicenseService licenseService;
    @Resource
    ServiceConfig serviceConfig;

    @GetMapping(value = "/")
    public List<License> getLicenses(@PathVariable(value = "organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    @GetMapping(value = "{clientType}")
    public License getLicense(@PathVariable("organizationId") String organizationId,
                              @PathVariable("licenseId") String licenseId,
                              @PathVariable("clientType") String clientType) {
        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

    @GetMapping("getProperty")
    public String getProperty() {
        return serviceConfig.getProperty();
    }

    @PostMapping(value = "{licenseId}")
    public String updateLicense(@PathVariable("licenseId") String licenseId) {
        return licenseId + " updated";
    }

    @PostMapping(value = "/")
    public String saveLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
        return "success!!";
    }

    @DeleteMapping(value = "{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is deleted!!!");
    }
}

