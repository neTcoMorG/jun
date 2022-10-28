package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GalleryRepository galleryRepository;

    @GetMapping
    public String homePage (Model model) {
        model.addAttribute("gallerys", galleryRepository.findAll());
        return "index";
    }

}
