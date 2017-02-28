package com.bbva.armadillomonitor.repository;

import com.bbva.armadillomonitor.domain.Application;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Application entity.
 */
@SuppressWarnings("unused")
public interface ApplicationRepository extends MongoRepository<Application,String> {

}
