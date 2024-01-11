package org.bs.rental.mapper.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BookMapperTests {

    @Autowired(required = false)
    private BookMapper bookMapper;

    // Book List Mapper Test
    @Test
    @DisplayName("책 리스트 매퍼 테스트")
    @Transactional
    public void bookListMapperTest(){

        // Given
        log.info("Book List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
       List<BookListDTO> list = bookMapper.bookList(pageRequestDTO);

        // Then
        log.info("list: " + list.toString());

        log.info("Book List Mapper Test Complete");
    }

    // Book Total Mapper Test
    @Test
    @DisplayName("책 토탈 매퍼 테스트")
    @Transactional
    public void bookTotalMapperTest(){

        // Given
        log.info("Book Total Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        int reuslt = bookMapper.bookTotal(pageRequestDTO);

        // Then
        log.info("result: " + reuslt);

        log.info("Book Total Mapper Test Complete");
    }

    // Book Create Mapper Test
    @Test
    @DisplayName("책 등록 매퍼 테스트")
    // @Transactional
    public void bookCreateMapperTest(){

        // Given
        log.info("Book Create Mapper Test Start");

        BookCreateDTO bookCreateDTO = BookCreateDTO.builder()
        .title("book1")
        .author("beomsu")
        .isbn("112-12")
        .publicationDate(LocalDate.of(2023, 12, 23))
        .publisher("blue")
        .language("한국어")
        .totalPages(117)
        .description("책 입니다.")
        .build();

        // When
        int result = bookMapper.bookCreate(bookCreateDTO);

        // Then
        assertEquals(1, result);

        log.info("Book Create Mapper Test Complete");
    }

    // Book Read Mapper Test
    @Test
    @DisplayName("책 조회 매퍼 테스트")
    @Transactional
    public void bookReadMapperTest(){

        // Given
        log.info("Book Read Mapper Test Start");

        Long bookNumber = 1L;

        // When
        BookDTO info =  bookMapper.bookRead(bookNumber);

        // Then
        log.info("information: " + info.toString());

        log.info("Book Read Mapper Test Complete");
    }

    // Book Update Mapper Test
    @Test
    @DisplayName("책 수정 매퍼 테스트")
    @Transactional
    public void bookUpdateMapperTest(){

        // Given
        log.info("Book Update Mapper Test Start");
        BookUpdateDTO bookUpdateDTO = BookUpdateDTO.builder()
        .bookNumber(1L)
        .title("bookbook2")
        .author("Beomsu")
        .isbn("ASVZX-1")
        .publicationDate(LocalDate.of(2011, 1, 8))
        .publisher("lbs")
        .language("한국어")
        .totalPages(221)
        .description("book")
        .build();

        // When
        int result = bookMapper.bookUpdate(bookUpdateDTO);

        // Then
        assertEquals(1, result);

        log.info("Book Update Mapper Test Complete");
    }

    // Book Delete Mapper Test
    @Test
    @DisplayName("책 삭제 매퍼 테스트")
    @Transactional
    public void bookDeleteMapperTest(){

        // Given
        log.info("Book Delete Mapper Test Start");

        Long bookNumber = 1L;

        // When
        int result = bookMapper.bookDelete(bookNumber);

        // Then
        assertEquals(1, result);

        log.info("Book Delete Mapper Test Complete");
    }
    
    // Book Status to Available
    @Test
    @DisplayName("대출 가능으로 상태 변경 매퍼 테스트")
    @Transactional
    public void bookStatusToAvailableMapperTest(){

        // Given
        log.info("Book Status To Available Mapper Test Start");

        Long bookNumber = 1L;

        // When
        int result = bookMapper.bookStatusToAvailable(bookNumber);

        // Then
        assertEquals(1, result);

        log.info("Book Status To Available Mapper Test Complete");

    }

    // Book Status To Borrow
    @Test
    @DisplayName("대출 중으로 상태 변경 매퍼 테스트")
    @Transactional
    public void bookStatusToBorrowedMapperTest(){

        // Given
        log.info("Book Status To Borrowed Mapper Test Start");

        Long bookNumber = 1L;

        // When
        int result = bookMapper.bookStatusToBorrowed(bookNumber);

        // Then
        assertEquals(1, result);

        log.info("Book Status To Borrowed Mapper Test Complete");
    }
    
}
