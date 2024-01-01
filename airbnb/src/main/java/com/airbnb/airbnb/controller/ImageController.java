package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.entity.Image;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public List<Image> uploadImage(@RequestPart(value = "image") List<MultipartFile> images,
                                   Stay stay) {
        return imageService.uploadImage(images, stay);
    }

}
