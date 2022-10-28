package com.igrallery.jun.domain.service;

import com.igrallery.jun.domain.dto.GalleryForm;
import com.igrallery.jun.domain.dto.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {

    void create (GalleryForm form);
    void uploadImages (Integer gid, List<MultipartFile> images, List<Size> sizes);

}
