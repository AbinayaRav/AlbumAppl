package com.AlbumApp.AlbumApplication.controller;

import com.AlbumApp.AlbumApplication.ResourceNotFoundException;
import com.AlbumApp.AlbumApplication.model.Album;
import com.AlbumApp.AlbumApplication.repository.AlbumRepository;
import com.AlbumApp.AlbumApplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Album")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;
    SongRepository songRepository;

    @GetMapping()
    List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/get/{id}")
    Album getAlbumById(@PathVariable(value = "id") Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        return album;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE )
    Album createNewAlbumInDB(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @PutMapping("/update/{id}")
    Album updateAlbumDetails(@PathVariable(value = "id") Long id, @RequestBody Album updatedAlbum) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        album.setAlbumLength(updatedAlbum.getAlbumLength());
        album.setAlbumName(updatedAlbum.getAlbumName());
        album.setPrice(updatedAlbum.getPrice());
        album.setProducer(album.getProducer());
        album.setRating(album.getRating());
        album.setRecordLabel(updatedAlbum.getRecordLabel());
        album.setReleaseDate(album.getReleaseDate());
        album.setListOfSongs(updatedAlbum.getListOfSongs());
        albumRepository.save(album);
        return album;
    }


    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteAlbum(@PathVariable(value = "id") Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        albumRepository.deleteAlbumSingerAssociation(id);
        albumRepository.delete(album);
        return ResponseEntity.ok().build();
    }

}
