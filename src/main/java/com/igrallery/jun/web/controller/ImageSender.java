package com.igrallery.jun.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image/")
@RequiredArgsConstructor
public class ImageSender {

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
        catch (Exception e) {
            throw new FileNotFoundException("이미지 파일이 없음");
        }
        throw new FileNotFoundException("이미지 파일이 없음");
    }
}
