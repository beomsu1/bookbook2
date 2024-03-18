package org.bs.rental.util.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class S3Util {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Client s3Client;

    // 업로드
    public void uploadFile(List<Path> filePath) {

        if (filePath == null || filePath.size() == 0) {
            return;
        }

        for (Path path : filePath) {

            // 객체 생성
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(path.toFile().getName())
                    .build();

            s3Client.putObject(request, path);
        }

    }

    // 삭제
    public void deleteFile(List<Path> filePath) {

        if (filePath == null || filePath.size() == 0) {
            return;
        }

        for (Path path : filePath) {

            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(path.toFile().getName())
                    .build();

            s3Client.deleteObject(request);
        }

    }


}
