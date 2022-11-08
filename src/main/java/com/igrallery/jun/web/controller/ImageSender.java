package com.igrallery.jun.web.controller;

import com.igrallery.jun.domain.entity.Image;
import com.igrallery.jun.domain.entity.Metadata;
import com.igrallery.jun.domain.repository.ImageRepository;
import com.igrallery.jun.domain.service.MetadataService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/image/")
@RequiredArgsConstructor
public class ImageSender {

    private final ImageRepository imageRepository;

    @Value("${file.img}") private String imgDir;

    @GetMapping("{id}")
    public ResponseEntity<Resource> send (@PathVariable String id) throws FileNotFoundException {
        try {
            Resource resource = new FileSystemResource(imgDir + id);

            if (resource.exists()) {
                HttpHeaders header = new HttpHeaders();
                Path filePath = Paths.get(imgDir + id);
                header.add("Content-Type", Files.probeContentType(filePath));

                return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
            }
         }
        catch (Exception e) { throw new FileNotFoundException("이미지 파일이 없음"); }
        throw new FileNotFoundException("이미지 파일이 없음");
    }

    @GetMapping("/meta")
    public ResponseEntity<?> getMetadata (@RequestParam Long id) {
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent() && image.get().getMetadata() != null) {
            return ResponseEntity.ok(mappedToMetaObject(image.get().getMetadata()));
        }
        return ResponseEntity.ok("empty");
    }

    private MetaObject mappedToMetaObject (Metadata metadata) {
        return new MetaObject(metadata.getName(), metadata.getDate(), metadata.getLat(), metadata.getLon(), metadata.getDevice());
    }

    @Data
    @ToString
    @AllArgsConstructor
    static class MetaObject {
        private String name;
        private String date;
        private Double lat;
        private Double lon;
        private String device;
    }
}
