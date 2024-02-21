package org.bs.rental.service.Book;

import org.apache.ibatis.annotations.Param;
import org.bs.rental.dto.book.*;
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
    PageResponseDTO<BookListByMemberDTO> listOfBookBorrowedByMember(PageRequestDTO pageRequestDTO, String id);

    // 회원이 반납한 책 리스트
    PageResponseDTO<BookListByMemberDTO> listOfBookReturnByMember(PageRequestDTO pageRequestDTO, String id);

}
