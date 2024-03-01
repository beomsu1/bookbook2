package org.bs.rental.controller;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
import org.bs.rental.service.board.BoardService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
@Log4j2
@EnableMethodSecurity
public class BoardController {

    private final BoardService boardService;

    // Board List
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void getBoardList(PageRequestDTO pageRequestDTO, Model model){

        log.info("GET | Board List Controller");

        PageResponseDTO<BoardListDTO> list = boardService.boardList(pageRequestDTO);

        model.addAttribute("board", list);

    }

    // Get Board Create
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void getBoardCreate(){

        log.info("GET | Board Create Controller");

    }

    // Get Board Read
    @GetMapping("read/{tno}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getBoardRead(@PathVariable("tno") Long tno, Model model){

        log.info("GET | Board Read Controller");

        BoardDTO info = boardService.boardRead(tno);

        model.addAttribute("board", info);

        return "/board/read";

    }

    // Get Board Update
    @GetMapping("update/{tno}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getBoardUpdade(@PathVariable("tno") Long tno, Model model){

        log.info("GET | Board Update Controller");

        BoardDTO info = boardService.boardRead(tno);

        model.addAttribute("board", info);

        return "/board/update";

    }

    // Post Board Create
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBoardCreate(BoardCreateDTO boardCreateDTO){

        log.info("POST | Board Create Controller");

        boardService.boardCreate(boardCreateDTO);

        return "redirect:/board/list";
    }

    // Post Board Update
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBoardUpdate(BoardUpdateDTO boardUpdateDTO){

        log.info("POST | Board Update Controller");

        boardService.boardUpdate(boardUpdateDTO);

        return "redirect:/board/read" + boardUpdateDTO.getTno();

    }

    // Post Board Delete
    @PostMapping("delete/{tno}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBoardDelete(@PathVariable("tno") Long tno){

        log.info("POST | Board Delete Controller");

        boardService.boardDelete(tno);

        return "redirect:/board/list";

    }

}
