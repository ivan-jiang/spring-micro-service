package com.ivan.licenses.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "licenses")
@NoArgsConstructor
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;
    @Column(name = "organization_id", nullable = false)
    private String organizationId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "license_type", nullable = false)
    private String licenseType;
    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;
    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;
    @Column(name = "comment", nullable = false)
    private String comment;
}
