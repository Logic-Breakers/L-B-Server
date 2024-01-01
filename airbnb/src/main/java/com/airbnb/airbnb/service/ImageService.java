package com.airbnb.airbnb.service;

import com.airbnb.airbnb.entity.Image;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.repository.ImageRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public List<Image> uploadImage(List<MultipartFile> multipartFiles, Stay stay) {
        log.info("이미지 업로드 중");
        List<Image> uploadImages = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFiles.size() > 10) {
                throw new BusinessLogicException(ExceptionCode.IMAGE_LIMIT_EXCEEDED);
            }
            validateFileExists(multipartFile);

            String fileName = renameFile(multipartFile.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                log.error("이미지 업로드 중 에러가 발생했습니다.");
            }

            String imageUrl = amazonS3Client.getUrl(bucketName, fileName).toString();
            Image uploadImage = createImage(imageUrl, stay);
            uploadImages.add(uploadImage);
            imageRepository.save(uploadImage);
        }
        log.info("이미지 업로드 완료");
        return uploadImages;

    }

    private Image createImage(String imageUrl, Stay stay) {
        Image image = new Image();
        image.setImageUrl(imageUrl);
        image.setUploadedAt(LocalDateTime.now());
        image.setStay(stay);
        return image;
    }

    private String renameFile(String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        String renameName = fileName + "_" + formattedDateTime;
        return renameName;
    }

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUND);
        }
    }
}