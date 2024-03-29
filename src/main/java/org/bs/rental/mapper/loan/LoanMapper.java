package org.bs.rental.mapper.loan;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.dto.loan.LoanReadDTO;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface LoanMapper {

    // Loan List
    List<LoanDTO> loanList(PageRequestDTO pageRequestDTO);

    // Loan Total
    int loanTotal(PageRequestDTO pageRequestDTO);

    // Book Borrow
    int bookBorrow(BookBorrowDTO borrowDTO);

    // Book Return
    int bookReturn(BookReturnDTO returnDTO);

    // 책 번호로 빌린 멤버 / '대출 중' 인 상태
    LoanReadDTO BorrowedByBookNumber(Long bookNumber);


}
