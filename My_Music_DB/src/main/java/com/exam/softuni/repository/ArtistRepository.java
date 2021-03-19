package com.exam.softuni.repository;

import com.exam.softuni.model.entity.Artist;
import com.exam.softuni.model.entity.enums.BandName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
    Artist findByName(BandName bandName);
}
