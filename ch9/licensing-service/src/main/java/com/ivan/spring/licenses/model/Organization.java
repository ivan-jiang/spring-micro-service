package com.ivan.spring.licenses.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Organization implements Serializable {
    private static final long serialVersionUID = -9028993830455144451L;
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}