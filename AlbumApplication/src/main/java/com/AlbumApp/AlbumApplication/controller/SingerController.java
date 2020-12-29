package com.AlbumApp.AlbumApplication.controller;

import com.AlbumApp.AlbumApplication.ResourceNotFoundException;
import com.AlbumApp.AlbumApplication.model.Singer;
import com.AlbumApp.AlbumApplication.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Singer")
public class SingerController {

    @Autowired
    SingerRepository singerRepository;

    @GetMapping
    List<Singer> getListOfAllSingers() {
        return singerRepository.findAll();
    }

    @GetMapping("/get/{id}")
    Singer getSingerById(@PathVariable(value = "id") Long id) {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found ", "Singer", id));
        System.out.println(singer.getListOfAlbums().size()+"********************************");
        return singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found ", "Singer ", id));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    Singer addNewSingerInDB(@RequestBody Singer singer) {
        return singerRepository.save(singer);
    }

    @PutMapping("/update/{id}")
    Singer updateSingerDetails(@PathVariable(value = "id") Long id, @RequestBody Singer updatedSinger) {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found ", "Singer", id));
        singer.setAge(updatedSinger.getAge());
        singer.setAwardsReceived(updatedSinger.getAwardsReceived());
        singer.setCountry(updatedSinger.getCountry());
        singer.setDebut(updatedSinger.getDebut());
        singer.setGender(updatedSinger.getGender());
        singer.setHobby(updatedSinger.getHobby());
        singer.setListOfAlbums(updatedSinger.getListOfAlbums());
        return singerRepository.save(singer);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteSingerById(@PathVariable(value = "id") Long id) {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found ", "Singer", id));
        singerRepository.deleteAlbumSingerAssociation(id);
        singerRepository.delete(singer);
        return ResponseEntity.ok().build();
    }
}
