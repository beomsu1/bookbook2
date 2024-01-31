package org.bs.rental.mapper.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoticeMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    // Notice List Mapper Test
    @Test
    @DisplayName("공지사항 리스트 매퍼 테스트")
    @Transactional
    public void noticeListMapperTest() {

        // Given
        log.info("Notice List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        List<NoticeListDTO> list = noticeMapper.noticeList(pageRequestDTO);

        // Then
        log.info("List: " + list);

        log.info("Notice List Mapper Test Complete");

    }

    // Notice Create Mapper Test
    @Test
    @DisplayName("공지사항 생성 매퍼 테스트")
    // @Transactional
    public void noticeCreateMapperTest() {

        // Given
        log.info("Notice Create Mapper Test Start");

        NoticeCreateDTO noticeCreateDTO = NoticeCreateDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .writer("admin")
                .build();

        // When
        int result = noticeMapper.noticeCreate(noticeCreateDTO);

        // Then
        assertEquals(1, result);

        log.info("Notice Create Mapper Test Complete");
    }

    // Notice Read Mapper Test
    @Test
    @DisplayName("공지사항 조회 매퍼 테스트")
    @Transactional
    public void noticeReadMapperTest() {

        // Given
        log.info("Notice Read Mapper Test Start");

        Long nno = 3L;

        // When
        NoticeDTO info = noticeMapper.noticeRead(nno);

        // Then
        log.info("Info: " + info);

        log.info("Notice Read Mapper Test Complete");
    }

    // Notice Update Mapper Test
    @Test
    @DisplayName("공지사항 수정 매퍼 테스트")
    @Transactional
    public void noticeUpdateMapperTest() {

        // Given
        log.info("Notice Update Mapper Test Start");

        NoticeUpdateDTO noticeDTO = NoticeUpdateDTO.builder()
                .title("Test Update Title")
                .content("Test Update Content")
                .nno(3L)
                .build();

        // When
        int result = noticeMapper.noticeUpdate(noticeDTO);

        // Then
        assertEquals(1, result);

        log.info("Notice Update Mapper Test Complete");
    }

    // Notice Delete Mapper Test
    @Test
    @DisplayName("공지사항 삭제 매퍼 테스트")
    @Transactional
    public void noticeDeleteMapperTest() {

        // Given
        log.info("Notice Delete Mapper Test Start");

        Long nno = 3L;

        // When
        int result = noticeMapper.noticeDelete(nno);

        // Then
        assertEquals(1, result);

        log.info("Notice Delete Mapper Test Complete");

    }

    // Notice Total
    @Test
    @DisplayName("공지사항 개수 매퍼 테스트")
    @Transactional
    public void noticeTotalMapperTest() {

        // Given
        log.info("Notice Total Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        int total = noticeMapper.noticeTotal(pageRequestDTO);

        // Then
        log.info("Total: " + total);

        log.info("Notice Total Mapper Test Complete");

    }

}
