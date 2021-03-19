package com.exam.softuni.model.entity;

import com.exam.softuni.model.entity.enums.BandName;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    private BandName name;
    private String careerInformation;

    public Artist() {
    }

    public Artist(BandName name, String careerInformation) {
        this.name = name;
        this.careerInformation = careerInformation;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false)
    public BandName getName() {
        return name;
    }

    public void setName(BandName name) {
        this.name = name;
    }

    @Column(name = "career_information", columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
