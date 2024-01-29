package org.bs.rental.mapper.notice;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoticeImageMapperTests {

    @Autowired
    private NoticeImageMapper noticeImageMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    // Insert Image
    @Test
    @DisplayName("이미지 삽입 매퍼 테스트")
    @Transactional
    public void insertImageMapperTest() {

        // Given
        log.info("Insert Image Mapper Test Start");

        NoticeCreateDTO noticeCreateDTO = NoticeCreateDTO.builder()
                .title("Test Title2")
                .writer("admin")
                .content("Test Content2")
                .fnames(List.of(UUID.randomUUID() + "_Image"))
                .build();

        // When
        List<String> fnames = noticeCreateDTO.getFnames();

        // 게시물 등록
        int count = noticeMapper.noticeCreate(noticeCreateDTO);

        log.info(noticeCreateDTO);

        if (count > 0 && noticeCreateDTO.getFnames() != null && !noticeCreateDTO.getFnames().isEmpty()) {

            Long nno = noticeCreateDTO.getNno();

            log.info(nno);

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "nno", "" + nno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            log.info(list);

            // 파일 등록
            noticeImageMapper.insertImage(list);

        }

        // Then
        log.info("Insert Image Mapper Test Complete");

    }

    // Delete Image
    @Test
    @DisplayName("이미지 삭제 매퍼 테스트")
    @Transactional
    public void deleteImageMapperTest() {

        // Given
        log.info("Delete Image Mapper Test Start");

        Long nno = 4L;

        // When
        noticeImageMapper.deleteImage(nno);

        // Then
        log.info("Delete Image Mapper Test Complete");
    }

}
