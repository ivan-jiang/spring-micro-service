package com.ivan.spring.specialroute.repository;

import com.ivan.spring.specialroute.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute, String> {
    AbTestingRoute findByServiceName(String serviceName);
}
