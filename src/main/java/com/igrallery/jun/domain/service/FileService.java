package com.igrallery.jun.domain.service;

import com.igrallery.jun.domain.dto.Size;
import com.igrallery.jun.domain.entity.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void saveImages (Gallery gallery, List<MultipartFile> images, List<String> itemTypes);
}
