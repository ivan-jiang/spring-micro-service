package com.spring.microservice.controller;

import com.spring.microservice.model.Licence;
import com.spring.microservice.service.LicenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationid}/licences")
public class LicenceController {
    @Resource
    LicenceService licenceService;

    @GetMapping("/{licenceId}")
    public Licence getLicence(@PathVariable String licenceId) {
        return licenceService.getLicence(licenceId);
    }
}
