package com.AlbumApp.AlbumApplication.View;


import com.AlbumApp.AlbumApplication.ResourceNotFoundException;
import com.AlbumApp.AlbumApplication.model.Album;
import com.AlbumApp.AlbumApplication.model.Song;
import com.AlbumApp.AlbumApplication.repository.AlbumRepository;
import com.AlbumApp.AlbumApplication.repository.SingerRepository;
import com.AlbumApp.AlbumApplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
public class SongView {

    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private SingerRepository singerRepository;

    @Autowired
    public SongView(SongRepository songRepository, AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    @GetMapping("/Song")
    public String getSongList(Model model) {
        model.addAttribute("song", songRepository.findAll());
        return "ListOfSongsView";
    }

    @GetMapping("/ViewSongsInAlbum/{id}")
    public String getSongsInAlbum(@PathVariable(value = "id") Long id, Model model) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        model.addAttribute("song", songRepository.findByAlbumAlbumId(album.getAlbumId()));
        model.addAttribute("flag", "true");
        return "ListOfSongsView";
    }

    @GetMapping("/ViewSongsOfSinger/{id}")
    public String getSongsOfSinger(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("song", songRepository.findBySingerSingerId(id));
        return "ListOfSongsView";
    }

    @GetMapping("/AddNewSong")
    public String openSongCreatePage(Song song, Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("singers", singerRepository.findAll());
        return "AddNewSongView";
    }

    @PostMapping("/createSong/{id}")
    String addSongForAlbum(@PathVariable(value = "id") Long id, @Valid @ModelAttribute("song") Song song, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "UpdateSongView";
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        song.setAlbum(album);
        if (album.getListOfSingers() == null || album.getListOfSingers().isEmpty()) {
            album.getListOfSingers().add(song.getSinger());
            album.setListOfSingers(album.getListOfSingers());
        }
        albumRepository.save(album);
        songRepository.save(song);
        return "redirect:/AlbumList";
    }

    @PostMapping("/createSong")
    String addSong(@Valid @ModelAttribute("song") Song song, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "UpdateSongView";
        }
        Album album = albumRepository.findById(song.getAlbum().getAlbumId()).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", song.getAlbum().getAlbumId()));
        album.getListOfSingers().add(song.getSinger());
        album.setListOfSingers(album.getListOfSingers());
        albumRepository.save(album);
        songRepository.save(song);

        return "redirect:/Song";
    }

    @GetMapping("/editSong/{id}")
    public String openUpdateSongForm(@PathVariable(value = "id") Long id, Model model) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song Not Found ", "Song : ", id));
        model.addAttribute("song", song);
        model.addAttribute("singers", singerRepository.findAll());
        return "UpdateSongView";
    }


    @GetMapping("/deleteSong/{id}")
    public String openUpdateSingerForm(@PathVariable(value = "id") Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song Not Found ", "Song : ", id));
        songRepository.delete(song);
        return "redirect:/Song";
    }


}
