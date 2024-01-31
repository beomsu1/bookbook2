package org.bs.rental.mapper.reply.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bs.rental.dto.reply.board.BoardReplyCreateDTO;
import org.bs.rental.dto.reply.board.BoardReplyDTO;
import org.bs.rental.dto.reply.board.BoardReplyListDTO;
import org.bs.rental.dto.reply.board.BoardReplyUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface BoardReplyMapper {

    // 게시물에 대한 댓글 목록
    List<BoardReplyListDTO> boardReplyList(@Param("tno") Long tno,
            @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);

    // 게시물 댓글 개수
    int boardReplyTotal(Long tno);

    // 게시물에 댓글 생성
    int boardReplyCreate(BoardReplyCreateDTO boardReplyCreateDTO);

    // 게시물에 대댓글 생성
    int boardReplyChildCreate(BoardReplyCreateDTO boardReplyCreateDTO);

    // 댓글, 대댓글 구분을 위한 gno값 변경
    int boardReplyGnoUpdate(Long rno);

    // 게시물 댓글 조회
    BoardReplyDTO boardReplyRead(Long rno);

    // 게시물 댓글 수정
    int boardReplyUpdate(BoardReplyUpdateDTO boardReplyUpdateDTO);

    // 게시물 댓글 삭제
    int boardReplyDelete(Long rno);

}
