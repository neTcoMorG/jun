package com.igrallery.jun.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image/")
@RequiredArgsConstructor
public class ImageSender {

    @GetMapping("{id}")
    public ResponseEntity<?> send (@PathVariable String id) {

        return ResponseEntity.ok("");
    }
}
