package com.AlbumApp.AlbumApplication.View;

import com.AlbumApp.AlbumApplication.ResourceNotFoundException;
import com.AlbumApp.AlbumApplication.model.Singer;
import com.AlbumApp.AlbumApplication.repository.SingerRepository;
import com.AlbumApp.AlbumApplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping
public class SingerView {

    private SingerRepository singerRepository;
    private SongRepository songRepository;

    @Autowired
    public SingerView(SingerRepository singerRepository, SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    @GetMapping("/Singers")
    public String getSingerList(Model model) {
        model.addAttribute("singer", singerRepository.findAll());
        return "ListOfSingersView";
    }

    @GetMapping("/AddNewSinger")
    public String openSingerCreatePage(Model model, Singer singer) {
        return "AddNewSingerView";
    }

    @PostMapping("/createSinger")
    String addSinger(@Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddNewSingerView";
        }
        singerRepository.save(singer);
        return "redirect:/Singers";
    }

    @GetMapping("/editSinger/{id}")
    public String openUpdateSingerForm(@PathVariable(value = "id") Long id, Model model) {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Singer Not Found ", "Singer : ", id));
        model.addAttribute("singer", singer);
        return "AddNewSingerView";
    }

    @GetMapping("/deleteSinger/{id}")
    public String openUpdateSingerForm(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Singer Not Found ", "Singer : ", id));
        if (!songRepository.findBySingerSingerId(id).isEmpty()) {
            //errors.rejectValue("deletion", "Delete Songs sung by the singer and then delete Singer");
            redirectAttributes.addFlashAttribute("errorMessage", "Delete Songs sung by the singer and then delete Singer!");
            return "redirect:/Singers";
        }
        singerRepository.delete(singer);
        return "redirect:/Singers";
    }


}
