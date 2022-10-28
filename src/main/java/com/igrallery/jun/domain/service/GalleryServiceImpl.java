package com.igrallery.jun.domain.service;

import com.igrallery.jun.domain.dto.GalleryForm;
import com.igrallery.jun.domain.dto.Size;
import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    @Override
    public void create (GalleryForm form) {
        galleryRepository.save(new Gallery(form.getName(), form.getSub()));
    }

    @Override
    public void uploadImages(Integer gid, List<MultipartFile> images, List<Size> sizes) {

    }
}
