package com.bbva.armadillomonitor.repository;

import com.bbva.armadillomonitor.domain.Project;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Project entity.
 */
@SuppressWarnings("unused")
public interface ProjectRepository extends MongoRepository<Project,String> {

}
