package org.bs.rental.mapper.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private BoardMapper boardMapper;

    // Board List Mapper Test
    @Test
    @DisplayName("게시판 리스트 매퍼 테스트")
    @Transactional
    public void boardListMapperTest(){

        // Given
        log.info("Board List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        List<BoardListDTO> list = boardMapper.boardList(pageRequestDTO);

        // Then
        log.info("List: " + list);

        log.info("Board List Mapper Test Complete");
        
    }
    
    // Board Create Mapper Test
    @Test
    @DisplayName("게시판 생성 매퍼 테스트")
    @Transactional
    public void boardCreateMapperTest(){

        // Given
        log.info("Board Create Mapper Test Start");

        BoardCreateDTO boardCreateDTO = BoardCreateDTO.builder()
        .title("Test Title")
        .content("Test Content")
        .writer("qjatn1221")
        .build();

        // When
        int result = boardMapper.boardCreate(boardCreateDTO);

        // Then
        assertEquals(1, result);

        log.info("Board Create Mapper Test Complete");
    }

    // Board Read Mapper Test
    @Test
    @DisplayName("게시판 조회 매퍼 테스트")
    @Transactional
    public void boardReadMapperTest(){

        // Given
        log.info("Board Read Mapper Test Start");

        Long tno = 1L;

        // When
        BoardDTO info = boardMapper.boardRead(tno);

        // Then
        log.info("Info: " + info);

        log.info("Board Read Mapper Test Complete");
    }

    // Board Update Mapper Test
    @Test
    @DisplayName("게시판 수정 매퍼 테스트")
    @Transactional
    public void boardUpdateMapperTest(){

        // Given
        log.info("Board Update Mapper Test Start");

        BoardUpdateDTO boardDTO = BoardUpdateDTO.builder()
        .title("Test Update Title")
        .content("Test Update Content")
        .tno(1L)
        .build();

        // When
        int result = boardMapper.boardUpdate(boardDTO);

        // Then
        assertEquals(1, result);

        log.info("Board Update Mapper Test Complete");
    }

    // Board Delete Mapper Test
    @Test
    @DisplayName("게시판 삭제 매퍼 테스트")
    @Transactional
    public void boardDeleteMapperTest(){

        // Given
        log.info("Board Delete Mapper Test Start");

        Long tno = 1L;

        // When
        int result = boardMapper.boardDelete(tno);

        // Then
        assertEquals(1, result);

        log.info("Board Delete Mapper Test Complete");

    }

    // Board Total
    @Test
    @DisplayName("게시판 개수 매퍼 테스트")
    @Transactional
    public void boardTotalMapperTest(){

        // Given
        log.info("Board Total Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        int total = boardMapper.boardTotal(pageRequestDTO);

        // Then
        log.info("Total: " + total);

        log.info("Board Total Mapper Test Complete");

    }
    
}
