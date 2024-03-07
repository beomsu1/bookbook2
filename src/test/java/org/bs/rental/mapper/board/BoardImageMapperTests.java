package org.bs.rental.mapper.board;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardImageMapperTests {

    @Autowired
    private BoardImageMapper boardImageMapper;

    @Autowired
    private BoardMapper boardMapper;

    // Insert Image
    @Test
    @DisplayName("이미지 삽입 매퍼 테스트")
    @Transactional
    public void insertImageMapperTest() {

        // Given
        log.info("Insert Image Mapper Test Start");

        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
                .title("Test Title2")
                .writer("beomsu")
                .content("Test Content2")
                .fnames(List.of(UUID.randomUUID() + "_Image"))
                .build();

        // When
        List<String> fnames = boardCreateDTO.getFnames();

        // 게시물 등록
        int count = boardMapper.boardCreate(boardCreateDTO);

        log.info(boardCreateDTO);

        if (count > 0 && boardCreateDTO.getFnames() != null && !boardCreateDTO.getFnames().isEmpty()) {

            Long tno = boardCreateDTO.getTno();

            log.info(tno);

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "tno", "" + tno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            log.info(list);

            // 파일 등록
            boardImageMapper.insertImage(list);

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

        Long tno = 1L;

        // When
        boardImageMapper.deleteImage(tno);

        // Then
        log.info("Delete Image Mapper Test Complete");
    }

}
