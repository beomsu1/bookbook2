package org.bs.rental.service.loan;

import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.dto.loan.LoanReadDTO;
import org.bs.rental.service.Loan.LoanService;
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
public class LoanServiceTests {

    @Autowired
    private LoanService loanService;

    // Loan List Service Test
    @Test
    @DisplayName("대출 리스트 서비스 테스트")
    @Transactional
    public void loanListServiceTest(){

        // Given
        log.info("Loan List Service Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<LoanDTO> list = loanService.loanList(pageRequestDTO);

        // Then
        log.info("List: " + list.toString());

        log.info("Loan List Service Test Complete");
    }

    // Book Borrow Service Test
    @Test
    @DisplayName("책 대출 서비스 테스트")
    @Transactional
    public void bookBorrowServiceTest(){

        // Given
        log.info("Book Borrow Serivce Test Start");

        BookBorrowDTO bookBorrowDTO = BookBorrowDTO.builder()
        .bookNumber(1L)
        .memberId("beomsu")
        .build();

        bookBorrowDTO.setDueDateFromSelectedPeriod(2);

        // When
        loanService.bookBorrow(bookBorrowDTO);

        // Then
        log.info("Book Borrow Service Test Complete ");
    }
    
    // Book Return Service Test
    @Test
    @DisplayName("책 반납 서비스 테스트")
    @Transactional
    public void bookReturnServiceTest(){

        // Given
        log.info("Book Return Service Test Start");

        BookReturnDTO bookReturnDTO = BookReturnDTO.builder()
        .bookNumber(1L)
        .memberId("beomsu")
        .build();

        // When
        loanService.bookReturn(bookReturnDTO);

        // Then
        log.info("Book Return Service Test Complete");

    }

    // Borrowed By Book Number
    @Test
    @DisplayName("책 번호로 멤버 조회 서비스 테스트")
    @Transactional
    public void BorrowedByBookNumber(){

        // Given
        log.info("Borrowed By Book Number Service Test Start");

        Long BookNumber = 1L;

        // When
        LoanReadDTO info = loanService.borrowedByBookNumber(BookNumber);

        // Then
        log.info("info: " + info);

        log.info("Borrowed By Book Number Service Test Complete");
    }
    
}
