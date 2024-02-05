package org.bs.rental.service.reply.board;

import java.util.List;

import org.bs.rental.dto.reply.board.BoardReplyCreateDTO;
import org.bs.rental.dto.reply.board.BoardReplyDTO;
import org.bs.rental.dto.reply.board.BoardReplyListDTO;
import org.bs.rental.dto.reply.board.BoardReplyUpdateDTO;
import org.bs.rental.mapper.reply.board.BoardReplyMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService{

    private final BoardReplyMapper boardReplyMapper;

    // 게시물에 대한 댓글 목록
    @Override
    public PageResponseDTO<BoardReplyListDTO> boardReplyList(Long tno, PageRequestDTO pageRequestDTO) {

        log.info("Board Reply List ServcieImpl Start");

        int total = boardReplyMapper.boardReplyTotal(tno);

        int pageNum = pageRequestDTO.getPage();

        // 끝 페이지 계산
        if(!pageRequestDTO.isReplyLast()){

            pageNum = (int) Math.ceil(total/(double) pageRequestDTO.getSize());

            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        pageRequestDTO.setPage(pageNum);

        List<BoardReplyListDTO> list = boardReplyMapper.boardReplyList(tno, pageRequestDTO);

        return PageResponseDTO.<BoardReplyListDTO>builder()
        .list(list)
        .total(total)
        .build();
    }

    // 댓글, 대댓글 생성
    @Override
    public void boardReplyCreate(BoardReplyCreateDTO boardReplyCreateDTO) {

        log.info("Board Reply Create ServiceImpl Start");

        // if gno = 0 -> 댓글, else 대댓글
        if(boardReplyCreateDTO.getGno() == 0) {

            boardReplyMapper.boardReplyCreate(boardReplyCreateDTO);

            boardReplyMapper.boardReplyGnoUpdate(boardReplyCreateDTO.getRno());

        } else {

            boardReplyMapper.boardReplyChildCreate(boardReplyCreateDTO);

        }

    }

    // 댓글, 대댓글 조회
    @Override
    public BoardReplyDTO boardReplyRead(Long rno) {

        log.info("Board Reply Read ServiceImpl Start");

        return boardReplyMapper.boardReplyRead(rno);
    }

    // 댓글, 대댓글 수정
    @Override
    public int boardReplyUpdate(BoardReplyUpdateDTO boardReplyUpdateDTO) {

        log.info("Board Reply Update ServiceImpl Start");

        return boardReplyMapper.boardReplyUpdate(boardReplyUpdateDTO);
    }

    // 댓글, 대댓글 삭제
    @Override
    public int boardReplyDelete(Long rno) {

        log.info("Board Reply Delete ServiceImpl Start");

        return boardReplyMapper.boardReplyDelete(rno);
    }


    
}
