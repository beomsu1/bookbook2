package org.bs.rental.mapper.reply;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bs.rental.dto.reply.board.BoardReplyCreateDTO;
import org.bs.rental.dto.reply.board.BoardReplyDTO;
import org.bs.rental.dto.reply.board.BoardReplyListDTO;
import org.bs.rental.dto.reply.board.BoardReplyUpdateDTO;
import org.bs.rental.mapper.reply.board.BoardReplyMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardReplyMapperTests {

    @Autowired
    private BoardReplyMapper boardReplyMapper;

    // 게시물에 대한 댓글 목록 매퍼 테스트
    @Test
    @DisplayName("게시물에 대한 댓글 목록 매퍼 테스트")
    @Transactional
    public void boardReplyListMapperTest(){

        // Given
        log.info("Board Reply List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        List<BoardReplyListDTO> list = boardReplyMapper.boardReplyList(1L, pageRequestDTO);

        // Then
        list.forEach(info -> log.info(info.toString()));

        log.info("Board Reply List Mapper Test Complete");
    }

    // 게시물에 대한 댓글 총 개수 매퍼 테스트
    @Test
    @DisplayName("게시물에 대한 댓글 총 개수 매퍼 테스트")
    @Transactional
    public void boardReplyTotalMapperTest(){

        // Given
        log.info("Board Reply Total Mapper Test Start");

        Long tno = 1L;

        // When
        int total = boardReplyMapper.boardReplyTotal(tno);

        // Then
        log.info("Total: " + total);

        log.info("Board Reply Total Mapper Test Complete");

    }


    // 댓글 생성 매퍼 테스트
    @Test
    @DisplayName("댓글 생성 매퍼 테스트")
    @Transactional
    public void boardReplyCreateMapperTest() {

        // Given
        log.info("Board Reply Create Mapper Test Start");

        BoardReplyCreateDTO boardReplyCreateDTO = BoardReplyCreateDTO.builder()
                .tno(1L)
                .reply("책 찾아보니까 정말 좋네요~")
                .replyer("beomsu")
                .build();

        // When
        int result = boardReplyMapper.boardReplyCreate(boardReplyCreateDTO);

        // gno == rno 변경
        int result2 = boardReplyMapper.boardReplyGnoUpdate(boardReplyCreateDTO.getRno());

        // Then
        assertEquals(1, result);
        assertEquals(1, result2);

        log.info("Board Reply Create Mapper Test Complete");

    }

    // 대댓글 생성 매퍼 테스트
    @Test
    @DisplayName("대댓글 생성 매퍼 테스트")
    @Transactional
    public void boardReplyChildCreateMapperTest() {

        // Given
        log.info("Board Reply Child Create Mapper Test Start");

        BoardReplyCreateDTO boardReplyCreateDTO = BoardReplyCreateDTO.builder()
                .tno(1L)
                .gno(1L)
                .reply("동의합니다~")
                .replyer("admin")
                .build();

        // When
        int result = boardReplyMapper.boardReplyChildCreate(boardReplyCreateDTO);

        // Then
        assertEquals(1, result);

        log.info("Board Reply Child Create Mapper Test Complete");
    }

    // 댓글, 대댓글 조회 매퍼 테스트
    @Test
    @DisplayName("댓글, 대댓글 조회 매퍼 테스트")
    @Transactional
    public void boardReplyReadMapperTest() {

        // Given
        log.info("Board Reply Read Mapper Test Start");

        Long rno = 1L;

        // When
        BoardReplyDTO info = boardReplyMapper.boardReplyRead(rno);

        // Then
        Assertions.assertAll(
                () -> assertEquals("반갑습니다~", info.getReply()),
                () -> assertEquals("admin", info.getReplyer()));

        log.info("Info: " + info.toString());

        log.info("Board Reply Read Mapper Test Complete");
    }

    // 댓글, 대댓글 수정 매퍼 테스트
    @Test
    @DisplayName("댓글, 대댓글 수정 매퍼 테스트")
    @Transactional
    public void boardReplyUpdateMapperTest(){

        // Given
        log.info("Board Reply Update Mapper Test Start");

        BoardReplyUpdateDTO boardReplyUpdateDTO = BoardReplyUpdateDTO.builder()
        .rno(1L)
        .reply("좋네요~")
        .build();

        // When
        int result = boardReplyMapper.boardReplyUpdate(boardReplyUpdateDTO);

        // Then
        assertEquals(1, result);

        log.info("Board Reply Update Mapper Test Complete");

    }

    // 댓글, 대댓글 삭제 매퍼 테스트
    @Test
    @DisplayName("댓글, 대댓글 삭제 매퍼 테스트")
    @Transactional
    public void boardReplyDeleteMapperTest(){

        // Given
        log.info("Board Reply Delete Mapper Test Start");

        Long rno = 1L;

        // When
        int result =  boardReplyMapper.boardReplyDelete(rno);

        // Then
        assertEquals(1, result);

        log.info("Board Reply Delete Mapper Test Complete");
    }


}
