package com.gyf.service;

import org.springframework.web.multipart.MultipartFile;
import com.gyf.entity.PitchDeck;

public interface PitchDeckService {

    PitchDeck uploadPitchDeck(
            Long startupId,
            MultipartFile file) throws Exception;

}