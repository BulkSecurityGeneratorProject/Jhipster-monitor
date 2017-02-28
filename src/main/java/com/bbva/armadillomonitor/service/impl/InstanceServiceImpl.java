package com.bbva.armadillomonitor.service.impl;

import com.bbva.armadillomonitor.service.InstanceService;
import com.bbva.armadillomonitor.domain.Instance;
import com.bbva.armadillomonitor.repository.InstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Instance.
 */
@Service
public class InstanceServiceImpl implements InstanceService{

    private final Logger log = LoggerFactory.getLogger(InstanceServiceImpl.class);
    
    @Inject
    private InstanceRepository instanceRepository;

    /**
     * Save a instance.
     *
     * @param instance the entity to save
     * @return the persisted entity
     */
    public Instance save(Instance instance) {
        log.debug("Request to save Instance : {}", instance);
        Instance result = instanceRepository.save(instance);
        return result;
    }

    /**
     *  Get all the instances.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<Instance> findAll(Pageable pageable) {
        log.debug("Request to get all Instances");
        Page<Instance> result = instanceRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one instance by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Instance findOne(String id) {
        log.debug("Request to get Instance : {}", id);
        Instance instance = instanceRepository.findOne(id);
        return instance;
    }

    /**
     *  Delete the  instance by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Instance : {}", id);
        instanceRepository.delete(id);
    }
}
