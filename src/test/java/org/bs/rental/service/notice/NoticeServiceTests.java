package org.bs.rental.service.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoticeServiceTests {

    @Autowired
    private NoticeService noticeService;

    // Notice List Service
    @Test
    @DisplayName("공지사항 리스트 서비스 테스트")
    @Transactional
    public void noticeListServiceTest() {

        // Given
        log.info("Notice List Service Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<NoticeListDTO> list = noticeService.noticeList(pageRequestDTO);

        // Then
        log.info(list);

        log.info("Notice List Service Test Complete");
    }

    // Notice Create Service
    @Test
    @DisplayName("공지사항 등록 서비스 테스트")
    @Transactional
    public void noticeCreateServiceTest() {

        // Given
        log.info("Notice Create Service Test Start");

        NoticeCreateDTO noticeCreateDTO = NoticeCreateDTO.builder()
                .title("Test Title3")
                .content("Test Content3")
                .writer("admin")
                .build();

        // When
        noticeService.noticeCreate(noticeCreateDTO);

        // Then
        log.info("Notice Create Service Test Complete");
    }

    // Notice Read Service
    @Test
    @DisplayName("공지사항 조회 서비스 테스트")
    @Transactional
    public void noticeReadServiceTest() {

        // Given
        log.info("Notice Read Service Test Start");

        Long nno = 3L;

        // When
        NoticeDTO info = noticeService.noticeRead(nno);

        // Then
        log.info("information: " + info.toString());

        log.info("Notice Read Service Test Complete");
    }

    // Notice Update Service
    @Test
    @DisplayName("공지사항 수정 서비스 테스트")
    @Transactional
    public void noticeUpdateServiceTest() {

        // Given
        log.info("notice Update Service Test Start");

        NoticeUpdateDTO noticeUpdateDTO = NoticeUpdateDTO.builder()
                .nno(3L)
                .title("Test Update Title3")
                .content("Test Update Content3")
                .build();

        // When
        noticeService.noticeUpdate(noticeUpdateDTO);

        // Then
        log.info("Notice Update Service Test Complete");
    }

    // Notice Delete Service
    @Test
    @DisplayName("공지사항 삭제 서비스 테스트")
    @Transactional
    public void noticeDeleteServiceTest() {

        // Given
        log.info("Notice Delete Service Test Strat");

        Long nno = 3L;

        // When
        int result = noticeService.noticeDelete(nno);

        // Then
        assertEquals(1, result);

        log.info("Notice Delete Service Test Complete");
    }

}
