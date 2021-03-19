package com.exam.softuni.service.impl;

import com.exam.softuni.model.entity.Album;
import com.exam.softuni.model.entity.Artist;
import com.exam.softuni.model.entity.User;
import com.exam.softuni.model.service.AlbumServiceModel;
import com.exam.softuni.repository.AlbumRepository;
import com.exam.softuni.service.AlbumService;
import com.exam.softuni.service.ArtistService;
import com.exam.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistService artistService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistService artistService, UserService userService, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel, String username) {
        Album album = modelMapper.map(albumServiceModel, Album.class);
        Artist artist = artistService.getArtistByBandName(albumServiceModel.getBandName());
        album.setArtist(artist);

        User user = userService.getUserByUsername(username);
        album.setAddedFrom(user);

        albumRepository.save(album);
    }
}
