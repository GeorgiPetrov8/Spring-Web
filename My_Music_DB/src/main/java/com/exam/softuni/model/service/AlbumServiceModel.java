package com.exam.softuni.model.service;

import com.exam.softuni.model.entity.enums.BandName;
import com.exam.softuni.model.entity.enums.GenreName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumServiceModel {
    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private int copies;
    private LocalDate releasedDate;
    private String producer;
    private BandName bandName;
    private GenreName genre;
    private String description;

    public AlbumServiceModel() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BandName getBandName() {
        return bandName;
    }

    public void setBandName(BandName bandName) {
        this.bandName = bandName;
    }

    public GenreName getGenre() {
        return genre;
    }

    public void setGenre(GenreName genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
