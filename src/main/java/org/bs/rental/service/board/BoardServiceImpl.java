package org.bs.rental.service.board;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.board.BoardCreateDTO;
import org.bs.rental.dto.board.BoardDTO;
import org.bs.rental.dto.board.BoardListDTO;
import org.bs.rental.dto.board.BoardUpdateDTO;
import org.bs.rental.mapper.board.BoardImageMapper;
import org.bs.rental.mapper.board.BoardMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private final BoardImageMapper boardImageMapper;

    // Board List
    @Override
    public PageResponseDTO<BoardListDTO> boardList(PageRequestDTO pageRequestDTO) {

        log.info("Board List Service Impl Start");

        List<BoardListDTO> list = boardMapper.boardList(pageRequestDTO);
        int total = boardMapper.boardTotal(pageRequestDTO);

        return PageResponseDTO.<BoardListDTO>builder()
                .list(list)
                .total(total)
                .build();
    }

    // Board Create
    @Override
    public void boardCreate(BoardCreateDTO boardCreateDTO) {

        log.info("Book Create Service Impl Start");

        List<String> fnames = boardCreateDTO.getFnames();

        // 게시물 등록
        int count = boardMapper.boardCreate(boardCreateDTO);

        if (count > 0 && boardCreateDTO.getFnames() != null && !boardCreateDTO.getFnames().isEmpty()) {

            Long tno = boardCreateDTO.getTno();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "tno", "" + tno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            boardImageMapper.insertImage(list);

        }

    }

    // Board Read
    @Override
    public BoardDTO boardRead(Long tno) {

        log.info("Board Read Service Impl Start");

        return boardMapper.boardRead(tno);
    }

    // Board Update
    @Override
    public void boardUpdate(BoardUpdateDTO boardUpdateDTO) {

        log.info("Board Update Service Impl Start");

        // 수정 업데이트
        int count = boardMapper.boardUpdate(boardUpdateDTO);

        // 기존파일 삭제
        boardImageMapper.deleteImage(boardUpdateDTO.getTno());

        List<String> fnames = boardUpdateDTO.getFnames();

        if (count > 0 && boardUpdateDTO.getFnames() != null && !boardUpdateDTO.getFnames().isEmpty()) {

            Long tno = boardUpdateDTO.getTno();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "tno", "" + tno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            boardImageMapper.insertImage(list);

        }

    }

    // Board Delete
    @Override
    public int boardDelete(Long tno) {

        log.info("Board Delete Service Impl Start");

        return boardMapper.boardDelete(tno);
    }

}
