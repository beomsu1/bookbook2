package org.bs.rental.service.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
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
public class boardServiceTests {

    @Autowired
    private BoardService boardService;

    // Board List Service
    @Test
    @DisplayName("게시판 리스트 서비스 테스트")
    @Transactional
    public void boardListServiceTest(){

        // Given
        log.info("Board List Service Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<BoardListDTO> list = boardService.boardList(pageRequestDTO);

        // Then
        log.info(list);

        log.info("Board List Service Test Complete");
    }

    // Board Create Service
    @Test
    @DisplayName("게시판 등록 서비스 테스트")
    @Transactional
    public void boardCreateServiceTest(){

        // Given
        log.info("Board Create Service Test Start");

        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
        .title("Test Title3")
        .content("Test Content3")
        .writer("qjatn1221")
        .build();

        // When
        boardService.boardCreate(boardCreateDTO);

        // Then
        log.info("Board Create Service Test Complete");
    }

    // Board Read Service
    @Test
    @DisplayName("게시판 조회 서비스 테스트")
    @Transactional
    public void boardReadServiceTest(){

        // Given
        log.info("Board Read Service Test Start");

        Long tno = 1L;

        // When
        BoardDTO info = boardService.boardRead(tno);

        // Then
        log.info("information: " + info.toString());

        log.info("Board Read Service Test Complete");
    }

    // Board Update Service
    @Test
    @DisplayName("게시판 수정 서비스 테스트")
    @Transactional
    public void boardUpdateServiceTest(){

        // Given
        log.info("board Update Service Test Start");

        BoardUpdateDTO boardUpdateDTO = BoardUpdateDTO.builder()
        .tno(1L)
        .title("Test Update Title3")
        .content("Test Update Content3")
        .build();

        // When
        boardService.boardUpdate(boardUpdateDTO);

        // Then
        log.info("Board Update Service Test Complete");
    }

    // Board Delete Service
    @Test
    @DisplayName("게시판 삭제 서비스 테스트")
    @Transactional
    public void boardDeleteServiceTest(){

        // Given
        log.info("Board Delete Service Test Strat");

        Long tno = 1L;

        // When
        int result = boardService.boardDelete(tno);
        
        // Then
        assertEquals(1, result);

        log.info("Board Delete Service Test Complete");
    }
    
}
