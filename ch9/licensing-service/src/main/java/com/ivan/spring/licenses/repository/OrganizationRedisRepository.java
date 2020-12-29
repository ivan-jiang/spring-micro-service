package com.ivan.spring.licenses.repository;

import com.ivan.spring.licenses.model.Organization;

public interface OrganizationRedisRepository {
    void save(Organization org);

    void update(Organization org);

    void delete(String id);

    Organization find(String id);
}
