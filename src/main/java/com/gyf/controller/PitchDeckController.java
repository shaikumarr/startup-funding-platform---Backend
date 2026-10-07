package com.gyf.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gyf.entity.PitchDeck;
import com.gyf.repository.PitchDeckRepository;
import com.gyf.service.PitchDeckService;

@RestController
@RequestMapping("/api/pitchdeck")
public class PitchDeckController {

    @Autowired
    private PitchDeckService pitchDeckService;

    @Autowired
    private PitchDeckRepository pitchDeckRepository;

    @PostMapping("/upload")
    public PitchDeck uploadFile(
            @RequestParam Long startupId,
            @RequestParam MultipartFile file)
            throws Exception {

        return pitchDeckService
                .uploadPitchDeck(startupId, file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable Long id)
            throws Exception {

        PitchDeck pitchDeck =
                pitchDeckRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("File Not Found"));

        Path path = Paths.get(
                pitchDeck.getFilePath());

        Resource resource =
                new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="
                                + pitchDeck.getFileName())
                .body(resource);
    }
}