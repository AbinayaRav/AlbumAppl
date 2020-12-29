package com.AlbumApp.AlbumApplication.controller;

import com.AlbumApp.AlbumApplication.ResourceNotFoundException;
import com.AlbumApp.AlbumApplication.model.Album;
import com.AlbumApp.AlbumApplication.model.Singer;
import com.AlbumApp.AlbumApplication.model.Song;
import com.AlbumApp.AlbumApplication.repository.AlbumRepository;
import com.AlbumApp.AlbumApplication.repository.SingerRepository;
import com.AlbumApp.AlbumApplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SongController {

    @Autowired
    SongRepository songRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SingerRepository singerRepository;


    @GetMapping("/Songs")
    List<Song> getListOfAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/Song/get/{custom}")
    Song getSongById(@PathVariable(value = "custom") String s) {
        Song song = songRepository.Q2(s);
        if (song == null) {
            throw new ResourceNotFoundException("Not Found ", "Song");
        }
        return song;
    }


    @PostMapping(value = "/Song/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Song createNewSongToAlbum1(@RequestBody Song song) {
        Album album = albumRepository.findById(song.getAlbum().getAlbumId()).orElseThrow(() -> new ResourceNotFoundException("Album Not found ", "Album Id : ", song.getAlbum().getAlbumId()));
        song.setAlbum(album);
        Singer singer = singerRepository.findById(song.getSinger().getSingerId()).orElseThrow(() -> new ResourceNotFoundException("Singer Not found ", "Singer Id : ", song.getSinger().getSingerId()));
        song.setSinger(singer);
        return songRepository.save(song);
    }

    @PutMapping("/Song/update/{id}")
    Song updateSongById(@PathVariable(value = "id") Long id, @RequestBody Song updateSong) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found ", "Song ", id));
        song.setBillBoard(updateSong.getBillBoard());
        song.setStyle(updateSong.getStyle());
        song.setCertification(updateSong.getCertification());
        song.setTitle(updateSong.getTitle());
        song.setTrackLength(updateSong.getTrackLength());
        Album album = albumRepository.findById(updateSong.getAlbum().getAlbumId()).orElseThrow(() -> new ResourceNotFoundException("Album Not found ", "Album Id : ", song.getAlbum().getAlbumId()));
        song.setAlbum(album);
        //Singer singer = singerController.updateSingerDetails(updateSong.getSinger().getSingerId(), updateSong.getSinger());
        Singer singer = singerRepository.findById(updateSong.getSinger().getSingerId()).orElseThrow(() -> new ResourceNotFoundException("Singer Not found ", "Singer Id : ", song.getSinger().getSingerId()));
        song.setSinger(singer);
        return songRepository.save(song);
    }

    @DeleteMapping("/Song/delete/{id}")
    ResponseEntity<?> deleteSongById(@PathVariable(value = "id") Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found ", "Song ", id));
        songRepository.delete(song);
        return ResponseEntity.ok().build();
    }

}
