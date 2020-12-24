package com.spring.microservice.model;

import lombok.Data;

@Data
public class Organization {
    private String id;
    private String name;
    private String contactEmail;
    private String contactPhone;
}
