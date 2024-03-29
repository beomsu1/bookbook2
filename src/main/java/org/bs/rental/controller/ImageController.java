package org.bs.rental.controller;

import java.util.List;

import org.bs.rental.util.image.ImageUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/image/")
@EnableMethodSecurity
public class ImageController {

    private final ImageUtil imageUtil;

    // 이미지 추가
    @PostMapping("upload")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<String> postImageupload(List<MultipartFile> files){

        log.info("POST | Image Upload Controller");

        List<String> name = imageUtil.uplaod(files);

        return name;

    }

    // 이미지 삭제
    @DeleteMapping("delete/{fname}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String deleteImage(@PathVariable("fname") List<String> fname){

        log.info("Delete | Image Delete Controller");

        imageUtil.deleteFile(fname);

        return "이미지 삭제 완료";
    }
    
}
