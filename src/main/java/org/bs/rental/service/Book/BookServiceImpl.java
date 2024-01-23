package org.bs.rental.service.Book;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookImageDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.mapper.book.BookImageMapper;
import org.bs.rental.mapper.book.BookMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    private final BookImageMapper bookImageMapper;

    // List
    @Override
    public PageResponseDTO<BookListDTO> bookList(PageRequestDTO pageRequestDTO) {

        log.info("Book List Service Impl Start");

        pageRequestDTO.setSize(9);

        int total = bookMapper.bookTotal(pageRequestDTO);
        List<BookListDTO> list = bookMapper.bookList(pageRequestDTO);

        return PageResponseDTO.<BookListDTO>builder()
                .total(total)
                .list(list)
                .build();
    }

    // Book Create
    @Override
    public void bookCreate(BookCreateDTO bookCreateDTO) {

        log.info("Book Create Service Impl Start");

        List<String> fnames = bookCreateDTO.getFnames();

        // 게시물 등록
        int count = bookMapper.bookCreate(bookCreateDTO);

        if (count > 0 && bookCreateDTO.getFnames() != null && !bookCreateDTO.getFnames().isEmpty()) {

            Long bookNumber = bookCreateDTO.getBookNumber();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "bookNumber", "" + bookNumber, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            bookImageMapper.insertImage(list);

        }
    }

    // Book Read
    @Override
    public BookDTO bookRead(Long bookNumber) {

        log.info("Book Read Service Impl Start");

        return bookMapper.bookRead(bookNumber);
    }

    // Book Update
    @Override
    public void bookUpdate(BookUpdateDTO bookUpdateDTO) {

        log.info("Book Update Service Impl Start");

        // 수정 업데이트
        int count = bookMapper.bookUpdate(bookUpdateDTO);

        // 기존파일 삭제
        bookImageMapper.deleteImage(bookUpdateDTO.getBookNumber());

        List<String> fnames = bookUpdateDTO.getFnames();

        if (count > 0 && bookUpdateDTO.getFnames() != null && !bookUpdateDTO.getFnames().isEmpty()) {

            Long bookNumber = bookUpdateDTO.getBookNumber();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "bookNumber", "" + bookNumber, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            bookImageMapper.insertImage(list);

        }

    }

    // Book Delete
    @Override
    public int bookDelete(Long bookNumber) {

        log.info("Book Delete Service Imple Start");

        return bookMapper.bookDelete(bookNumber);
    }

    // Book Status To Available
    @Override
    public int bookStatusToAvailable(Long bookNumber) {

        return bookMapper.bookStatusToAvailable(bookNumber);
    }

    // Book Status To Borrow
    @Override
    public int bookStatusToBorrowed(Long bookNumber) {

        return bookMapper.bookStatusToBorrowed(bookNumber);
    }

}
