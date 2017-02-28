package com.bbva.armadillomonitor.web.rest;

import com.bbva.armadillomonitor.ArmadilloMonitorApp;

import com.bbva.armadillomonitor.domain.Instance;
import com.bbva.armadillomonitor.repository.InstanceRepository;
import com.bbva.armadillomonitor.service.InstanceService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InstanceResource REST controller.
 *
 * @see InstanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmadilloMonitorApp.class)
public class InstanceResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final String DEFAULT_MANAGEMENT_URL = "AAAAA";
    private static final String UPDATED_MANAGEMENT_URL = "BBBBB";

    private static final String DEFAULT_HEALTH_URL = "AAAAA";
    private static final String UPDATED_HEALTH_URL = "BBBBB";

    private static final String DEFAULT_SERVICE_URL = "AAAAA";
    private static final String UPDATED_SERVICE_URL = "BBBBB";

    private static final String DEFAULT_METADATA = "AAAAA";
    private static final String UPDATED_METADATA = "BBBBB";

    @Inject
    private InstanceRepository instanceRepository;

    @Inject
    private InstanceService instanceService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInstanceMockMvc;

    private Instance instance;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InstanceResource instanceResource = new InstanceResource();
        ReflectionTestUtils.setField(instanceResource, "instanceService", instanceService);
        this.restInstanceMockMvc = MockMvcBuilders.standaloneSetup(instanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Instance createEntity() {
        Instance instance = new Instance()
                .name(DEFAULT_NAME)
                .managementUrl(DEFAULT_MANAGEMENT_URL)
                .healthUrl(DEFAULT_HEALTH_URL)
                .serviceUrl(DEFAULT_SERVICE_URL)
                .metadata(DEFAULT_METADATA);
        return instance;
    }

    @Before
    public void initTest() {
        instanceRepository.deleteAll();
        instance = createEntity();
    }

    @Test
    public void createInstance() throws Exception {
        int databaseSizeBeforeCreate = instanceRepository.findAll().size();

        // Create the Instance

        restInstanceMockMvc.perform(post("/api/instances")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(instance)))
                .andExpect(status().isCreated());

        // Validate the Instance in the database
        List<Instance> instances = instanceRepository.findAll();
        assertThat(instances).hasSize(databaseSizeBeforeCreate + 1);
        Instance testInstance = instances.get(instances.size() - 1);
        assertThat(testInstance.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInstance.getManagementUrl()).isEqualTo(DEFAULT_MANAGEMENT_URL);
        assertThat(testInstance.getHealthUrl()).isEqualTo(DEFAULT_HEALTH_URL);
        assertThat(testInstance.getServiceUrl()).isEqualTo(DEFAULT_SERVICE_URL);
        assertThat(testInstance.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    public void getAllInstances() throws Exception {
        // Initialize the database
        instanceRepository.save(instance);

        // Get all the instances
        restInstanceMockMvc.perform(get("/api/instances?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(instance.getId())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].managementUrl").value(hasItem(DEFAULT_MANAGEMENT_URL.toString())))
                .andExpect(jsonPath("$.[*].healthUrl").value(hasItem(DEFAULT_HEALTH_URL.toString())))
                .andExpect(jsonPath("$.[*].serviceUrl").value(hasItem(DEFAULT_SERVICE_URL.toString())))
                .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA.toString())));
    }

    @Test
    public void getInstance() throws Exception {
        // Initialize the database
        instanceRepository.save(instance);

        // Get the instance
        restInstanceMockMvc.perform(get("/api/instances/{id}", instance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instance.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.managementUrl").value(DEFAULT_MANAGEMENT_URL.toString()))
            .andExpect(jsonPath("$.healthUrl").value(DEFAULT_HEALTH_URL.toString()))
            .andExpect(jsonPath("$.serviceUrl").value(DEFAULT_SERVICE_URL.toString()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA.toString()));
    }

    @Test
    public void getNonExistingInstance() throws Exception {
        // Get the instance
        restInstanceMockMvc.perform(get("/api/instances/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateInstance() throws Exception {
        // Initialize the database
        instanceService.save(instance);

        int databaseSizeBeforeUpdate = instanceRepository.findAll().size();

        // Update the instance
        Instance updatedInstance = instanceRepository.findOne(instance.getId());
        updatedInstance
                .name(UPDATED_NAME)
                .managementUrl(UPDATED_MANAGEMENT_URL)
                .healthUrl(UPDATED_HEALTH_URL)
                .serviceUrl(UPDATED_SERVICE_URL)
                .metadata(UPDATED_METADATA);

        restInstanceMockMvc.perform(put("/api/instances")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInstance)))
                .andExpect(status().isOk());

        // Validate the Instance in the database
        List<Instance> instances = instanceRepository.findAll();
        assertThat(instances).hasSize(databaseSizeBeforeUpdate);
        Instance testInstance = instances.get(instances.size() - 1);
        assertThat(testInstance.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInstance.getManagementUrl()).isEqualTo(UPDATED_MANAGEMENT_URL);
        assertThat(testInstance.getHealthUrl()).isEqualTo(UPDATED_HEALTH_URL);
        assertThat(testInstance.getServiceUrl()).isEqualTo(UPDATED_SERVICE_URL);
        assertThat(testInstance.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    public void deleteInstance() throws Exception {
        // Initialize the database
        instanceService.save(instance);

        int databaseSizeBeforeDelete = instanceRepository.findAll().size();

        // Get the instance
        restInstanceMockMvc.perform(delete("/api/instances/{id}", instance.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Instance> instances = instanceRepository.findAll();
        assertThat(instances).hasSize(databaseSizeBeforeDelete - 1);
    }
}
