package com.igrallery.jun.domain.service;

import com.drew.imaging.ImageProcessingException;
import com.igrallery.jun.domain.entity.Gallery;
import com.igrallery.jun.domain.entity.Image;
import com.igrallery.jun.domain.entity.ItemType;
import com.igrallery.jun.domain.entity.Metadata;
import com.igrallery.jun.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceDiskImpl implements FileService {

    private final ImageRepository imageRepository;
    private final MetadataService metadataService;

    @Value("${file.img}") private String imgDir;

    @Override
    @Transactional
    public void saveImages (Gallery gallery, List<MultipartFile> images, List<String> itemTypes) {
        List<MultipartFile> safeImages = cutting(images);
        if (safeImages.size() != itemTypes.size()) {
            throw new IllegalStateException("오염된 전송");
        }

        for (int i=0; i< safeImages.size(); i++) {
            if (!safeImages.get(i).getName().isEmpty() && !itemTypes.get(i).isEmpty()) {
                if (!safeImages.get(i).isEmpty()) {
                    String originalName = safeImages.get(i).getOriginalFilename();
                    String uuid = UUID.randomUUID().toString();
                    String ext = originalName.substring((originalName.lastIndexOf(".")));
                    String savedName = uuid + ext;
                    String fullPath = imgDir + savedName;

                    try {
                        safeImages.get(i).transferTo(new File(fullPath));
                        Image img = new Image(gallery, ItemType.valueOf(itemTypes.get(i)), i+1, originalName, savedName, fullPath);
                        img = imageRepository.save(img);
                        metadataService.apply(img);
                    }
                    catch (IOException e) { throw new IllegalStateException("파일 저장 오류"); }
                    catch (ImageProcessingException e) { throw new IllegalStateException("METADATA 추출 에러"); }
                }
            }
        }
    }

    private List<MultipartFile> cutting (List<MultipartFile> images) {
        List<MultipartFile> result = new ArrayList<>();
        images.forEach(image -> {
            if (image.getSize() > 0) {
                result.add(image);
            }
        });
        return result;
    }
}
