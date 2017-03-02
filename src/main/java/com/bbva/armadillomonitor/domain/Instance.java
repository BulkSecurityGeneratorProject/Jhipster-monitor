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

    @Field("metadata")
    private String metadata;

    @Field("armadillo_url")
    private String armadillo_url;

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

    public String getArmadillo_url() {
        return armadillo_url;
    }

    public Instance armadillo_url(String armadillo_url) {
        this.armadillo_url = armadillo_url;
        return this;
    }

    public void setArmadillo_url(String armadillo_url) {
        this.armadillo_url = armadillo_url;
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
            ", metadata='" + metadata + "'" +
            ", armadillo_url='" + armadillo_url + "'" +
            '}';
    }
}
