package com.ivan.spring.licenses.repository;

import com.ivan.spring.licenses.model.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository, InitializingBean {
    private static final String HASH_NAME = "organization_v1";
    private RedisTemplate<String, Organization> redisTemplate;
    private HashOperations<String, String, Organization> hashOperations;

    public OrganizationRedisRepositoryImpl() {

    }

    @Autowired
    public OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void update(Organization org) {
        save(org);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(HASH_NAME, id);
    }

    @Override
    public Organization find(String id) {
        return hashOperations.get(HASH_NAME, id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hashOperations = redisTemplate.opsForHash();
    }
}
