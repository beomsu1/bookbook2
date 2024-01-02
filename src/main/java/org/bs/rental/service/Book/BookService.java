package org.bs.rental.service.Book;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookService {

    // List
    PageResponseDTO<BookListDTO> bookList(PageRequestDTO pageRequestDTO);

    // Book Create
    int bookCreate(BookCreateDTO bookCreateDTO);

    // Book Read
    BookDTO bookRead(Long bookNumber);

    // Book Update
    int bookUpdate(BookUpdateDTO bookUpdateDTO);

    // Book Delete
    int bookDelete(Long bookNumber);

    // Status To Available
    int bookStatusToAvailable(@Param("bookNumber") Long bookNumber);

    // Status To Borrow
    int bookStatusToBorrowed(@Param("bookNumber") Long bookNumber);

    // 전체 도서 가져오기
    void booksCreate(List<BookDTO> books);

}
