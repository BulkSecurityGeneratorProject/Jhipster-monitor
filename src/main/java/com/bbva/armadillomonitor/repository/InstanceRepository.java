package com.bbva.armadillomonitor.repository;

import com.bbva.armadillomonitor.domain.Instance;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Instance entity.
 */
@SuppressWarnings("unused")
public interface InstanceRepository extends MongoRepository<Instance,String> {

}
