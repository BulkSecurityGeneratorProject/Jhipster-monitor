package com.bbva.armadillomonitor.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Instance.
 */

@Document(collection = "instance")
public class Instance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("management_url")
    private String managementUrl;

    @Field("health_url")
    private String healthUrl;

    @Field("service_url")
    private String serviceUrl;

    @Field("metadata")
    private String metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Instance name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagementUrl() {
        return managementUrl;
    }

    public Instance managementUrl(String managementUrl) {
        this.managementUrl = managementUrl;
        return this;
    }

    public void setManagementUrl(String managementUrl) {
        this.managementUrl = managementUrl;
    }

    public String getHealthUrl() {
        return healthUrl;
    }

    public Instance healthUrl(String healthUrl) {
        this.healthUrl = healthUrl;
        return this;
    }

    public void setHealthUrl(String healthUrl) {
        this.healthUrl = healthUrl;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public Instance serviceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getMetadata() {
        return metadata;
    }

    public Instance metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instance instance = (Instance) o;
        if(instance.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, instance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Instance{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", managementUrl='" + managementUrl + "'" +
            ", healthUrl='" + healthUrl + "'" +
            ", serviceUrl='" + serviceUrl + "'" +
            ", metadata='" + metadata + "'" +
            '}';
    }
}
