package com.exam.softuni.model.entity;

import com.exam.softuni.model.entity.enums.GenreName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {
    private String name;
    private String imageUrl;
    private String description;
    private int copies;
    private BigDecimal price;
    private LocalDate releasedDate;
    private String producer;
    private GenreName genre;
    private Artist artist;
    private User addedFrom;

    public Album() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "copies", nullable = false)
    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "released_date", nullable = false)
    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Column(name = "producer")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre", nullable = false)
    public GenreName getGenre() {
        return genre;
    }

    public void setGenre(GenreName genre) {
        this.genre = genre;
    }

    @ManyToOne
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne
    public User getAddedFrom() {
        return addedFrom;
    }

    public void setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
    }
}
