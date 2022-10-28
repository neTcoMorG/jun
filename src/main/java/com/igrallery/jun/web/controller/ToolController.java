package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.dto.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/tool")
public class ToolController {

    @GetMapping("{id}")
    public String toolPage (@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "tool";
    }

    @PostMapping
    public String saveImages (@RequestPart List<Size> sizes, @RequestPart List<MultipartFile> images) {
        return "";
    }
}
