package com.bbva.armadillomonitor.service;

import com.bbva.armadillomonitor.domain.Instance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Instance.
 */
public interface InstanceService {

    /**
     * Save a instance.
     *
     * @param instance the entity to save
     * @return the persisted entity
     */
    Instance save(Instance instance);

    /**
     *  Get all the instances.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Instance> findAll(Pageable pageable);

    /**
     *  Get the "id" instance.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Instance findOne(String id);

    /**
     *  Delete the "id" instance.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
