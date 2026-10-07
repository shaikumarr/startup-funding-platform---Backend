package com.gyf.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gyf.entity.PitchDeck;
import com.gyf.repository.PitchDeckRepository;

@Service
public class PitchDeckServiceImpl implements PitchDeckService {

    @Autowired
    private PitchDeckRepository repository;

    @Override
    public PitchDeck uploadPitchDeck(
            Long startupId,
            MultipartFile file) throws Exception {

        String uploadDir =
                System.getProperty("user.dir")
                + "/uploads/";

        File directory = new File(uploadDir);

        if(!directory.exists()) {
            directory.mkdirs();
        }

        String filePath =
                uploadDir + file.getOriginalFilename();

        file.transferTo(new File(filePath));

        PitchDeck pitchDeck = new PitchDeck();

        pitchDeck.setStartupId(startupId);
        pitchDeck.setFileName(
                file.getOriginalFilename());
        pitchDeck.setFilePath(filePath);

        return repository.save(pitchDeck);
    }
}