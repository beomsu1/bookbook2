package org.bs.rental.service.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.bs.rental.dto.book.*;
import org.bs.rental.service.Book.BookService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    // Book List Service
    @Test
    @DisplayName("책 리스트 서비스 테스트")
    @Transactional
    public void bookListServiceTest(){

        // Given
        log.info("Book List Service Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<BookListDTO> list = bookService.bookList(pageRequestDTO);

        // Then
        log.info(list);

        log.info("Book List Service Test Complete");
    }

    // Book Create Service
    @Test
    @DisplayName("책 등록 서비스 테스트")
    @Transactional
    public void bookCreateServiceTest(){

        // Given
        log.info("Book Create Service Test Start");

        BookCreateDTO bookCreateDTO = BookCreateDTO.builder()
        .title("beomsu")
        .author("Boemsu")
        .isbn("AKCZOA-1")
        .publicationDate(LocalDate.of(1998, 12, 21))
        .publisher("lbs")
        .language("korean")
        .totalPages("26")
        .description("maple")
        .build();

        // When
        bookService.bookCreate(bookCreateDTO);

        // Then
        log.info("Book Service Service Test Complete");
    }

    // Book Read Service
    @Test
    @DisplayName("책 조회 서비스 테스트")
    @Transactional
    public void bookReadServiceTest(){

        // Given
        log.info("Book Read Service Test Start");

        Long bookNumber = 2L;

        // When
        BookDTO info = bookService.bookRead(bookNumber);

        // Then

        log.info("information: " + info.toString());

        log.info("Book Read Service Test Complete");
    }

    // Book Update Service
    @Test
    @DisplayName("책 수정 서비스 테스트")
    @Transactional
    public void bookUpdateServiceTest(){

        // Given
        log.info("Book Update Service Test Start");

        BookUpdateDTO bookUpdateDTO = BookUpdateDTO.builder()
        .bookNumber(2L)
        .title("maple")
        .author("범수")
        .isbn("AKKZLA-1")
        .publicationDate(LocalDate.of(1998, 12, 21))
        .publisher("lbs")
        .language("korean")
        .totalPages("25")
        .description("maple1")
        .build();

        // When
        bookService.bookUpdate(bookUpdateDTO);

        // Then

        log.info("Book Update Service Test Complete");
    }

    // Book Delete Service
    @Test
    @DisplayName("책 삭제 서비스 테스트")
    @Transactional
    public void bookDeleteServiceTest(){

        // Given
        log.info("Book Delete Service Test Strat");

        Long BookNumber = 2L;

        // When
        int result = bookService.bookDelete(BookNumber);
        
        // Then
        assertEquals(1, result);

        log.info("Book Delete Service Test Complete");
    }

    // Book Status To Available Service
    @Test
    @DisplayName("대출 가능으로 상태 변경 서비스 테스트")
    @Transactional
    public void bookStatusToAvailableServiceTest(){

        // Given
        log.info("Book Stauts To Available Service Test Start");

        Long bookNumber = 1L;

        // When
        int result = bookService.bookStatusToAvailable(bookNumber);

        // Then
        assertEquals(1,result);

        log.info("Book Status To Available Service Test Complete");
    }

    // Book Status To Borrow Service
    @Test
    @DisplayName("대출 중으로 상태 변경 서비스 테스트")
    @Transactional
    public void bookStatusToBorrowedServiceTest(){

        // Given
        log.info("Book Status To Borrowed Service Test Start");

        Long bookNumber = 1L;

        // When
        int result = bookService.bookStatusToBorrowed(bookNumber);

        // Then
        assertEquals(1,result);

        log.info("Book Status To Borrowed Service Test Complete");
    }

    // 회원이 빌린 책 리스트
    @Test
    @DisplayName("회원이 빌린 책 리스트 서비스 테스트")
    @Transactional
    public void listOfBookBorrowedByMemberServiceTest(){

        // Given
        log.info("List Of Book Borrowed By Member Service Test Start");

        String id = "admin";
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<BookListByMemberDTO> list = bookService.listOfBookBorrowedByMember(pageRequestDTO,id);

        // Then
        log.info("List: " + list);

        log.info("List Of Book Borrowed By Member Service Test Complete");

    }
}
