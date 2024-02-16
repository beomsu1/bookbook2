package org.bs.rental.controller.reply;

import org.bs.rental.dto.reply.board.BoardReplyCreateDTO;
import org.bs.rental.dto.reply.board.BoardReplyDTO;
import org.bs.rental.dto.reply.board.BoardReplyListDTO;
import org.bs.rental.dto.reply.board.BoardReplyUpdateDTO;
import org.bs.rental.service.reply.board.BoardReplyService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board/reply/")
@Log4j2
@RequiredArgsConstructor
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    // Board Reply List
    @GetMapping("{tno}/list")
    public PageResponseDTO<BoardReplyListDTO> getBoardReplyList(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO) {

        log.info("GET | Board Reply List Controller");

        return boardReplyService.boardReplyList(tno, pageRequestDTO);

    }

    // Board Reply Create
    @PostMapping("{tno}/create")
    public ResponseEntity<String> postBoardReplyCreate(@PathVariable("tno") Long tno, @RequestBody BoardReplyCreateDTO boardReplyCreateDTO){

        log.info("POST | Board Reply Create Controller");

        boardReplyService.boardReplyCreate(boardReplyCreateDTO);

        return ResponseEntity.ok("댓글 등록이 완료되었습니다.");

    }

    // Board Reply Read
    @GetMapping("read/{rno}")
    public BoardReplyDTO getBoardReplyRead(@PathVariable("rno") Long rno){

        log.info("GET | Board REply Read Controller");

        return boardReplyService.boardReplyRead(rno);
    }

    // Board Reply Update
    @PutMapping("update/{rno}")
    public ResponseEntity<String> putBoardReplyUpdate(@PathVariable("rno") Long rno, BoardReplyUpdateDTO boardReplyUpdateDTO){

        log.info("PUT | Board Reply Update Controller");

        boardReplyService.boardReplyUpdate(boardReplyUpdateDTO);

        return ResponseEntity.ok("댓글 수정이 완료되었습니다.");
    }

    // Board Reply Delete
    @DeleteMapping("delete/{rno}")
    public ResponseEntity<String> deleteBoardReplyDelete(@PathVariable("rno") Long rno){

        log.info("DELETE | Board Reply Delete Controller");

        boardReplyService.boardReplyDelete(rno);

        return ResponseEntity.ok("댓글 삭제가 완료되었습니다.");
    }


}
