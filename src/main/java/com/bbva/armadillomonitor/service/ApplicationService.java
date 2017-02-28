package com.bbva.armadillomonitor.service;

import com.bbva.armadillomonitor.domain.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Application.
 */
public interface ApplicationService {

    /**
     * Save a application.
     *
     * @param application the entity to save
     * @return the persisted entity
     */
    Application save(Application application);

    /**
     *  Get all the applications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Application> findAll(Pageable pageable);

    /**
     *  Get the "id" application.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Application findOne(String id);

    /**
     *  Delete the "id" application.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
