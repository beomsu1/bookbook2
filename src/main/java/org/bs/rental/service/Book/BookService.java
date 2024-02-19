package org.bs.rental.service.Book;

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
    void bookCreate(BookCreateDTO bookCreateDTO);

    // Book Read
    BookDTO bookRead(Long bookNumber);

    // Book Update
    void bookUpdate(BookUpdateDTO bookUpdateDTO);

    // Book Delete
    int bookDelete(Long bookNumber);

    // Status To Available
    int bookStatusToAvailable(Long bookNumber);

    // Status To Borrow
    int bookStatusToBorrowed(Long bookNumber);

    // 회원이 빌린 책 리스트
    PageResponseDTO<BookListDTO> listOfBookBorrowedByMember(PageRequestDTO pageRequestDTO, String id);

}
