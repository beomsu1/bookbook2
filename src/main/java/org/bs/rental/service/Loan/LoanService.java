package org.bs.rental.service.Loan;

import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.dto.loan.LoanReadDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoanService {

    // Loan List
    PageResponseDTO<LoanDTO> loanList(PageRequestDTO pageRequestDTO);

    // Book Borrow
    void bookBorrow(BookBorrowDTO borrowDTO);

    // Book Return
    void bookReturn(BookReturnDTO returnDTO);

    // Borrowed By Book Number
    LoanReadDTO borrowedByBookNumber(Long bookNumber);
}
