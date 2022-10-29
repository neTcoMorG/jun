package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.dto.Size;
import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.repository.GalleryRepository;
import com.igrallery.jun.domain.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/tool")
@RequiredArgsConstructor
public class ToolController {

    private final FileService fileService;
    private final GalleryRepository galleryRepository;

    @GetMapping("{id}")
    public String toolPage (@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "tool";
    }

    @PostMapping("{id}")
    public String saveImages (@PathVariable(name = "id") Long gid, @RequestPart List<String> items,
                              @RequestPart List<MultipartFile> images,
                              HttpServletResponse response) throws IOException {
        Optional<Gallery> galleryOptional = galleryRepository.findById(gid);
        if (galleryOptional.isEmpty()) {
            response.sendError(404);
            return null;
        }

        fileService.saveImages(galleryOptional.get(), images, items);
        return "redirect:/gallery/" + gid;
    }
}
