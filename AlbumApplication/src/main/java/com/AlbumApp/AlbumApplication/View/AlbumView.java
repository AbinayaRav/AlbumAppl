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
public class AlbumView {

    private AlbumRepository albumRepository;
    private SingerRepository singerRepository;

    @Autowired
    public AlbumView(AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;
    }

    @GetMapping("/") //This endpoint is added to prevent 404 error while executing jar file (unable to access static Index.html)
    String getIndex() {
        return "Index";
    }

    @GetMapping("/Home")
    String get() {
        return "Home";
    }

    @GetMapping("/AlbumList")
    public String getAlbumList(Model model) {
        model.addAttribute("album", albumRepository.findAll());
        return "ListOfAlbumsView";
    }

    @GetMapping("/AddNewAlbum")
    public String openAlbumCreatePage(Album album, Model model) {
        model.addAttribute("singers", singerRepository.findAll());
        model.addAttribute("album", new Album());
        return "AddNewAlbumView";
    }

    @PostMapping("/createAlbum")
    String addAlbum(@Valid @ModelAttribute("album") Album album, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "UpdateAlbumView";
        }

        albumRepository.save(album);
        return "redirect:AlbumList";
    }

    @GetMapping("/editAlbum/{id}")
    public String openUpdateAlbumForm(@PathVariable(value = "id") Long id, Model model) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        model.addAttribute("album", album);
        return "UpdateAlbumView";
    }

    @GetMapping("/deleteAlbum/{id}")
    public String openUpdateAlbumForm(@PathVariable(value = "id") Long id) {
        System.out.println("What is the id :" + id);
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        albumRepository.deleteAlbumSingerAssociation(id);
        albumRepository.delete(album);
        return "redirect:/AlbumList";
    }

    @GetMapping("/AddSongToAlbum/{id}")
    public String addSongThroughAlbum(@PathVariable(value = "id") Long id, Model model) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album Not Found ", "Album : ", id));
        model.addAttribute("album", album);
        model.addAttribute("singers", singerRepository.findAll());
        model.addAttribute("song", new Song());
        return "AddNewSongView";
    }
/*
    @GetMapping("/ViewSingersInAlbum")
    public String viewSingersInAlbum(@Valid Album album, Model model) {
        System.out.println(album.toString()+"*************************");
        model.addAttribute("singers", album.getListOfSingers());
        return "ListOfSingersView";
    }*/

}
