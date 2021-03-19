package com.exam.softuni.service;

import com.exam.softuni.model.entity.Artist;
import com.exam.softuni.model.entity.enums.BandName;

public interface ArtistService {
    void initArtists();

    Artist getArtistByBandName(BandName bandName);
}
