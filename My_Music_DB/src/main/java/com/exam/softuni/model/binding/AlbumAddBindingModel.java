package com.exam.softuni.model.binding;

import com.exam.softuni.model.entity.enums.BandName;
import com.exam.softuni.model.entity.enums.GenreName;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private int copies;
    private LocalDate releasedDate;
    private String producer;
    private BandName bandName;
    private GenreName genreName;
    private String description;

    public AlbumAddBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Size(min = 5, message = "Image Url length must be minimum 5 characters")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Positive
    @NotBlank
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotBlank
    @Min(10)
    @Size(min = 10)
    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @PastOrPresent
    @NotBlank
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

    @NotNull
    public BandName getBandName() {
        return bandName;
    }

    public void setBandName(BandName bandName) {
        this.bandName = bandName;
    }

    @NotNull
    public GenreName getGenreName() {
        return genreName;
    }

    public void setGenreName(GenreName genreName) {
        this.genreName = genreName;
    }

    @Size(min = 5, message = "Description min length must be minimum 5 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
