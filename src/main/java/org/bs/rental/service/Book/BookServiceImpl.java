package org.bs.rental.service.Book;

import java.util.List;

import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.mapper.book.BookMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookMapper bookMapper;

    // List
    @Override
    public PageResponseDTO<BookListDTO> bookList(PageRequestDTO pageRequestDTO) {

        log.info("Book List Service Impl Start");

        int total = bookMapper.bookTotal(pageRequestDTO);
        List<BookListDTO> list = bookMapper.bookList(pageRequestDTO);

        return PageResponseDTO.<BookListDTO>builder()
        .total(total)
        .list(list)
        .build();
    }

    // Book Create
    @Override
    public int bookCreate(BookCreateDTO bookCreateDTO) {

        log.info("Book Create Service Impl Start");

        return bookMapper.bookCreate(bookCreateDTO);
    }

    // Book Read
    @Override
    public BookDTO bookRead(Long bookNumber) {

        log.info("Book Read Service Impl Start");

        return bookMapper.bookRead(bookNumber);
    }

    // Book Update
    @Override
    public int bookUpdate(BookUpdateDTO bookUpdateDTO) {

        log.info("Book Update Service Impl Start");

        return bookMapper.bookUpdate(bookUpdateDTO);
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

    // 전체 도서 가져오기
    @Override
    public void booksCreate(List<BookDTO> books) {

        for(BookDTO book : books){

            BookCreateDTO bookCreateDTO = BookCreateDTO.builder()
            .title(book.getTitle())
            .author(book.getAuthor())
            .isbn(book.getIsbn())
            .publicationDate(book.getPublication_date())
            .publisher(book.getPublisher())
            .language(book.getLanguage())
            .totalPages(book.getTotal_pages())
            .thumbnailUrl(book.getThumbnail_url())
            .description(book.getDescription())
            .build();

            bookCreate(bookCreateDTO);
        }
    }
    
}
