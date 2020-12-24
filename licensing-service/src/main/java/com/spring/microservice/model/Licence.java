package com.spring.microservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Licence {
    private String licenceId;
    private String organizationId;
    private String productName;
    private String licenceType;
}
