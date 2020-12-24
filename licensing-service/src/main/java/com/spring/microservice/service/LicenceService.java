package com.spring.microservice.service;

import com.spring.microservice.model.Licence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LicenceService {
    public Licence getLicence(String id) {
        return Licence.builder().licenceId(id).licenceType("PerSeat")
                .productName("Test Product Name").build();
    }

    public void addLicence(Licence licence) {

    }

    public void updateLicence(Licence licence) {
    }

    public void deleteLicence(Licence licence) {

    }
}
