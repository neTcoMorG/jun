package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.dto.GalleryForm;
import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.repository.GalleryRepository;
import com.igrallery.jun.domain.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;
    private final GalleryRepository galleryRepository;

    @Value("${file.thumbnail}") private String thumbnailDir;

    @PostMapping("/thumbnail/{gid}")
    public ResponseEntity<?> setThumbnail(@ModelAttribute String imgSrc, @PathVariable Long gid,
                                          HttpServletResponse response) {

        Optional<Gallery> galleryOptional = galleryRepository.findById(gid);
        FileOutputStream outputStream;

        try {
            if (galleryOptional.isEmpty()) { response.sendError(404); }

            imgSrc = imgSrc.replaceAll("data:image/png;base64,", "");
            byte[] file = Base64.decodeBase64(imgSrc);
            String savedName = UUID.randomUUID().toString();

            outputStream = new FileOutputStream(thumbnailDir + savedName + ".png");
            outputStream.write(file);
            outputStream.close();

            galleryOptional.get().setThumbnail(savedName);
        }
        catch (IOException ex) {
            throw new IllegalStateException("썸네일 저장 실패");
        }

        return ResponseEntity.ok("1");
    }

    @PostMapping
    public String createGallery (@ModelAttribute @Valid GalleryForm form) {
        Long id = galleryService.create(form);
        return "redirect:/tool/" + id;
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
