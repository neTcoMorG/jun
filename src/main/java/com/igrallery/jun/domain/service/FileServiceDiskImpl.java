package com.igrallery.jun.domain.service;

import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.entity.Image;
import com.igrallery.jun.domain.entity.ItemType;
import com.igrallery.jun.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceDiskImpl implements FileService {

    private final ImageRepository imageRepository;

    @Value("${file.img}") private String imgDir;

    @Override
    public void saveImages (Gallery gallery, List<MultipartFile> images, List<String> itemTypes) {
        for (int i=0; i<images.size(); i++) {
            if (images.get(i).isEmpty() || itemTypes.get(i).isEmpty()) {
                throw new IllegalStateException("IMAGES DATA RECV ERROR FROM FILESERVICE");
            }
            String originalName = images.get(i).getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = originalName.substring((originalName.lastIndexOf(".")));
            String savedName = uuid + ext;
            String fullPath = imgDir + savedName;

            try {
                String it = itemTypes.get(i);
                images.get(i).transferTo(new File(fullPath));
                Image entity = new Image(gallery, ItemType.getType(it), originalName, savedName, fullPath);
                imageRepository.save(entity);
            }
            catch (IOException ex) {
                throw new IllegalStateException("이미지 저장 실패");
            }
        }
    }
}
