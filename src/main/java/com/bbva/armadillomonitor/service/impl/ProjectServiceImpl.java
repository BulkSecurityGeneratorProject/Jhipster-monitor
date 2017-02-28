package com.bbva.armadillomonitor.service.impl;

import com.bbva.armadillomonitor.service.ProjectService;
import com.bbva.armadillomonitor.domain.Project;
import com.bbva.armadillomonitor.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Project.
 */
@Service
public class ProjectServiceImpl implements ProjectService{

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
    
    @Inject
    private ProjectRepository projectRepository;

    /**
     * Save a project.
     *
     * @param project the entity to save
     * @return the persisted entity
     */
    public Project save(Project project) {
        log.debug("Request to save Project : {}", project);
        Project result = projectRepository.save(project);
        return result;
    }

    /**
     *  Get all the projects.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<Project> findAll(Pageable pageable) {
        log.debug("Request to get all Projects");
        Page<Project> result = projectRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one project by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Project findOne(String id) {
        log.debug("Request to get Project : {}", id);
        Project project = projectRepository.findOne(id);
        return project;
    }

    /**
     *  Delete the  project by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.delete(id);
    }
}
