package org.bs.rental.service.board;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    // Board List
    PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO);

    // Board Create
    void boardCreate(BoardCreateDTO boardCreateDTO);

    // Board Read
    BoardDTO boardRead(Long tno);

    // Board Update
    void boardUpdate(BoardUpdateDTO boardUpdateDTO);

    // Board Delete
    int boardDelete(Long tno);
}
