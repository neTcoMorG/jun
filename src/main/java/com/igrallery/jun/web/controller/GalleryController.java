package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.dto.GalleryForm;
import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.repository.GalleryRepository;
import com.igrallery.jun.domain.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;
    private final GalleryRepository galleryRepository;

    @PostMapping
    public String createGallery (@RequestBody @Valid GalleryForm form) {
        galleryService.create(form);
        return "redirect://";
    }
    
    @GetMapping("{id}")
    public String showGallery (@PathVariable Long id, Model model, HttpServletResponse response) throws IOException {
        Optional<Gallery> galleryOptional = galleryRepository.findById(id);
        if (galleryOptional.isEmpty()) {
            response.sendError(404);
            return null;
        }

        model.addAttribute("images", galleryOptional.get().getImages());
        return "show";
    }
}
