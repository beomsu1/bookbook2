package org.bs.rental.mapper.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.book.BookCreateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BookImageMapperTests {

    @Autowired
    private BookImageMapper bookImageMapper;

    @Autowired
    private BookMapper bookMapper;

    // Insert Image
    @Test
    @DisplayName("이미지 삽입 매퍼 테스트")
    @Transactional
    public void insertImageMapperTest() {

        // Given
        log.info("Insert Image Mapper Test Start");

        BookCreateDTO bookCreateDTO = BookCreateDTO.builder()
                .title("book1")
                .author("beomsu")
                .isbn("AHHGB-12")
                .publicationDate(LocalDate.of(2023, 12, 23))
                .publisher("blue")
                .language("한국어")
                .totalPages("117쪽")
                .description("책 입니다.")
                .fnames(List.of(UUID.randomUUID()+"_Image"))
                .build();

        // When
        List<String> fnames = bookCreateDTO.getFnames();

        // 게시물 등록
        int count = bookMapper.bookCreate(bookCreateDTO);

        log.info(bookCreateDTO);

        if (count > 0 && bookCreateDTO.getFnames() != null && !bookCreateDTO.getFnames().isEmpty()) {

            Long bookNumber = bookCreateDTO.getBookNumber();

            log.info(bookNumber);

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

            String uuid = str.substring(0, 36);

            String fname = str.substring(37);

            // 맵들을 리스트로 묶기
            return Map.of("uuid", uuid, "fname", fname, "bookNumber", "" + bookNumber, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            
            log.info(list);

            // 파일 등록
            bookImageMapper.insertImage(list);

        }

        // Then
        log.info("Insert Image Mapper Test Complete");

    }

    // Delete Image
    @Test
    @DisplayName("이미지 삭제 매퍼 테스트")
    @Transactional
    public void deleteImageMapperTest(){

        // Given
        log.info("Delete Image Mapper Test Start");
        
        Long bookNumber = 1L;

        // When
        bookImageMapper.deleteImage(bookNumber);

        // Then
        log.info("Delete Image Mapper Test Complete");
    }

}
