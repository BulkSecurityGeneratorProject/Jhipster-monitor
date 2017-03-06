package com.bbva.armadillomonitor.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Application.
 */

@Document(collection = "application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("environment")
    private String environment;

    @Field("availability")
    private String availability;

    @Field("environment_type")
    private String environment_type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Application name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public Application environment(String environment) {
        this.environment = environment;
        return this;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAvailability() {
        return availability;
    }

    public Application availability(String availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getEnvironment_type() {
        return environment_type;
    }

    public Application environment_type(String environment_type) {
        this.environment_type = environment_type;
        return this;
    }

    public void setEnvironment_type(String environment_type) {
        this.environment_type = environment_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application application = (Application) o;
        if(application.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, application.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Application{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", environment='" + environment + "'" +
            ", availability='" + availability + "'" +
            ", environment_type='" + environment_type + "'" +
            '}';
    }
}
