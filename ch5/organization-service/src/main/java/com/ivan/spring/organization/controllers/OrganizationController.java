package com.ivan.spring.organization.controllers;


import com.ivan.spring.organization.model.Organization;
import com.ivan.spring.organization.services.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "v1/organizations")
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.getOrg(organizationId);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        organizationService.updateOrg(org);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.POST)
    public void saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrg(org);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("orgId") String orgId, @RequestBody Organization org) {
        organizationService.deleteOrg(org);
    }
}
