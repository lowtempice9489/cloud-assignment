package com.example.cloudassignment.storage;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3StorageService {

    private static final Duration PRESIGNED_URL_DURATION = Duration.ofDays(7);

    private final S3Template s3Template;

    @Value("${app.aws.s3.bucket-name}")
    private String bucketName;

    public String uploadProfileImage(Long memberId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("프로필 이미지 파일은 비어 있을 수 없습니다");
        }

        String objectKey = createObjectKey(memberId, file);
        try {
            s3Template.upload(bucketName, objectKey, file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("프로필 이미지 업로드에 실패했습니다", e);
        }
        return objectKey;
    }

    public String createPresignedUrl(String objectKey) {
        URL url = s3Template.createSignedGetURL(
                bucketName,
                objectKey,
                PRESIGNED_URL_DURATION
        );
        return url.toString();
    }

    private String createObjectKey(Long memberId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = getExtension(originalFilename);

        return "profile-images/"
                +memberId
                + "/"
                + UUID.randomUUID()
                + extension;
    }

    private String getExtension(String originalFilename) {
        if (originalFilename == null || !originalFilename.contains(".")) {
            return "";
        }
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

}
