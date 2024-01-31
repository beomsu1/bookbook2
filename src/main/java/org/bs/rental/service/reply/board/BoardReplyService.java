package org.bs.rental.service.reply.board;

import org.bs.rental.dto.reply.board.BoardReplyCreateDTO;
import org.bs.rental.dto.reply.board.BoardReplyDTO;
import org.bs.rental.dto.reply.board.BoardReplyListDTO;
import org.bs.rental.dto.reply.board.BoardReplyUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardReplyService {

    // 게시물에 대한 댓글 목록
    PageResponseDTO<BoardReplyListDTO> boardReplyList(Long tno, PageRequestDTO pageRequestDTO);

    // 게시물에 댓글 생성
    void boardReplyCreate(BoardReplyCreateDTO boardReplyCreateDTO);

    // 게시물 댓글 조회
    BoardReplyDTO boardReplyRead(Long rno);

    // 게시물 댓글 수정
    int boardReplyUpdate(BoardReplyUpdateDTO boardReplyUpdateDTO);

    // 게시물 댓글 삭제
    int boardReplyDelete(Long rno);

}
