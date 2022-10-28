package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.dto.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/tool")
public class ToolController {

    @GetMapping
    public String toolPage () {
        return "tool";
    }

    @PostMapping
    public String saveImages (@RequestPart List<Size> sizes, @RequestPart List<MultipartFile> images) {
        return "";
    }
}
