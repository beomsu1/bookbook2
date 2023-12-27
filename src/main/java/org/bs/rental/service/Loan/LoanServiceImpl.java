package org.bs.rental.service.Loan;

import java.util.List;

import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.mapper.book.BookMapper;
import org.bs.rental.mapper.loan.LoanMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoanServiceImpl implements LoanService{

    private final LoanMapper loanMapper;

    private final BookMapper bookMapper;

    // Loan List
    @Override
    public PageResponseDTO<LoanDTO> loanList(PageRequestDTO pageRequestDTO) {

        log.info("Loan List Service Impl Start");

        List<LoanDTO> list = loanMapper.loanList(pageRequestDTO);
        int total = loanMapper.loanTotal(pageRequestDTO);

        return PageResponseDTO.<LoanDTO>builder()
        .list(list)
        .total(total)
        .build();
        
    }

    // Book Borrow
    @Override
    public void bookBorrow(BookBorrowDTO borrowDTO) {

        log.info("Book Borrow Impl Start");

        loanMapper.bookBorrow(borrowDTO);

        bookMapper.bookStatusToBorrowed(borrowDTO.getBookNumber());

    }

    // Book Return
    @Override
    public void bookReturn(BookReturnDTO returnDTO) {

        log.info("Book Return Impl Start");

        loanMapper.bookReturn(returnDTO);

        bookMapper.bookStatusToAvailable(returnDTO.getBookNumber());

    }
    
}
