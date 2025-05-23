package com.cvds.parcial.back_parcial_3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "specialties")
public class Specialty {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String doctor;
    private String location;

    public Specialty() {}

    public Specialty(String name, String description, String imageUrl, String doctor, String location) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.doctor = doctor;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
