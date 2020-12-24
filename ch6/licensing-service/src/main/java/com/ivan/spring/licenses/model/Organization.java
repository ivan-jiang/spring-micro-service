package com.ivan.spring.licenses.model;

import lombok.Data;

@Data
public class Organization {
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}