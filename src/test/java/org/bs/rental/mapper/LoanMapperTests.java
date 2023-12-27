package org.bs.rental.mapper;

import java.util.List;

import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.mapper.book.BookMapper;
import org.bs.rental.mapper.loan.LoanMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LoanMapperTests {

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private BookMapper bookMapper;

    // Loan List Mapper Test
    @Test
    @DisplayName("대출 리스트 매퍼 테스트")
    @Transactional
    public void LoanListMapperTest(){

        // Given
        log.info("Loan List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        List<LoanDTO> list = loanMapper.loanList(pageRequestDTO);

        // Then
        log.info("List: " + list.toString());

        log.info("Loan List Mapper Test Complete");
    
    }

    // Loan Total Mapper Test
    @Test
    @DisplayName("대출 토탈 매퍼 테스트")
    @Transactional
    public void LoanTotalMapperTest(){

        // Given
        log.info("Loan Total Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        int total = loanMapper.loanTotal(pageRequestDTO);

        // Then
        log.info("Total: " + total);
        
        log.info("Loan Total Mapper Test Complete");

    }

    // Book Borrow Mapper Test
    @Test
    @DisplayName("책 대출 매퍼 테스트")
    @Transactional
    public void bookBorrowMapperTest(){

        // Given
        log.info("Book Borrow Mapper Test Start");

        BookBorrowDTO borrowDTO = BookBorrowDTO.builder()
        .bookNumber(1L)
        .memberId("beomsu1221")
        .build();

        borrowDTO.setDueDateFromSelectedPeriod(1);

        // When
        loanMapper.bookBorrow(borrowDTO);

        bookMapper.bookStatusToBorrowed(borrowDTO.getBookNumber());

        // Then
        log.info("Book Borrow Mapper Test Complete");
    }

    // Book Return Mapper Test
    @Test
    @DisplayName("책 반납 매퍼 테스트")
    @Transactional
    public void bookReturnMapperTest(){

        // Given
        log.info("Book Return Mapper Test Start");

        BookReturnDTO bookReturnDTO =  BookReturnDTO.builder()
        .bookNumber(1L)
        .build();

        // When
        loanMapper.bookReturn(bookReturnDTO);

        bookMapper.bookStatusToAvailable(bookReturnDTO.getBookNumber());

        // Then
        log.info("Book Return Mapper Test Complete");
    }

}
