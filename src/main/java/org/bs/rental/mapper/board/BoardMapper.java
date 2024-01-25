package org.bs.rental.mapper.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface BoardMapper {

    // Board List
    List<BoardListDTO> boardList(PageRequestDTO pageRequestDTO);

    // BoardTotal
    int boardTotal (PageRequestDTO pageRequestDTO);

    // Board Create
    int boardCreate(BoardCreateDTO boardCreateDTO);

    // Board Read
    BoardDTO boardRead(Long tno);

    // Board Update
    int boardUpdate(BoardUpdateDTO boardUpdateDTO);

    // Board Delete
    int boardDelete(Long tno);
    
}
