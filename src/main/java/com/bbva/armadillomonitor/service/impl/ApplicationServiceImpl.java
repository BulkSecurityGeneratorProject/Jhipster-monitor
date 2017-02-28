package com.bbva.armadillomonitor.service.impl;

import com.bbva.armadillomonitor.service.ApplicationService;
import com.bbva.armadillomonitor.domain.Application;
import com.bbva.armadillomonitor.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Application.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{

    private final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    
    @Inject
    private ApplicationRepository applicationRepository;

    /**
     * Save a application.
     *
     * @param application the entity to save
     * @return the persisted entity
     */
    public Application save(Application application) {
        log.debug("Request to save Application : {}", application);
        Application result = applicationRepository.save(application);
        return result;
    }

    /**
     *  Get all the applications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<Application> findAll(Pageable pageable) {
        log.debug("Request to get all Applications");
        Page<Application> result = applicationRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one application by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Application findOne(String id) {
        log.debug("Request to get Application : {}", id);
        Application application = applicationRepository.findOne(id);
        return application;
    }

    /**
     *  Delete the  application by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Application : {}", id);
        applicationRepository.delete(id);
    }
}
