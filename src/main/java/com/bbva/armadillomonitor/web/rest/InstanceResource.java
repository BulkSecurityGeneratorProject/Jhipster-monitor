package com.bbva.armadillomonitor.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bbva.armadillomonitor.domain.Instance;
import com.bbva.armadillomonitor.service.InstanceService;
import com.bbva.armadillomonitor.web.rest.util.HeaderUtil;
import com.bbva.armadillomonitor.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Instance.
 */
@RestController
@RequestMapping("/api")
public class InstanceResource {

    private final Logger log = LoggerFactory.getLogger(InstanceResource.class);
        
    @Inject
    private InstanceService instanceService;

    /**
     * POST  /instances : Create a new instance.
     *
     * @param instance the instance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instance, or with status 400 (Bad Request) if the instance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instances")
    @Timed
    public ResponseEntity<Instance> createInstance(@RequestBody Instance instance) throws URISyntaxException {
        log.debug("REST request to save Instance : {}", instance);
        if (instance.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("instance", "idexists", "A new instance cannot already have an ID")).body(null);
        }
        Instance result = instanceService.save(instance);
        return ResponseEntity.created(new URI("/api/instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("instance", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instances : Updates an existing instance.
     *
     * @param instance the instance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instance,
     * or with status 400 (Bad Request) if the instance is not valid,
     * or with status 500 (Internal Server Error) if the instance couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instances")
    @Timed
    public ResponseEntity<Instance> updateInstance(@RequestBody Instance instance) throws URISyntaxException {
        log.debug("REST request to update Instance : {}", instance);
        if (instance.getId() == null) {
            return createInstance(instance);
        }
        Instance result = instanceService.save(instance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("instance", instance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instances : get all the instances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of instances in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/instances")
    @Timed
    public ResponseEntity<List<Instance>> getAllInstances(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Instances");
        Page<Instance> page = instanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /instances/:id : get the "id" instance.
     *
     * @param id the id of the instance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instance, or with status 404 (Not Found)
     */
    @GetMapping("/instances/{id}")
    @Timed
    public ResponseEntity<Instance> getInstance(@PathVariable String id) {
        log.debug("REST request to get Instance : {}", id);
        Instance instance = instanceService.findOne(id);
        return Optional.ofNullable(instance)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /instances/:id : delete the "id" instance.
     *
     * @param id the id of the instance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instances/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstance(@PathVariable String id) {
        log.debug("REST request to delete Instance : {}", id);
        instanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("instance", id.toString())).build();
    }

}
