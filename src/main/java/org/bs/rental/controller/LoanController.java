package org.bs.rental.controller;

import org.bs.rental.dto.loan.BookBorrowDTO;
import org.bs.rental.dto.loan.BookReturnDTO;
import org.bs.rental.dto.loan.LoanDTO;
import org.bs.rental.service.Loan.LoanService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/loan/")
public class LoanController {

    private final LoanService loanService;

    // Loan List
    @GetMapping("list")
    public void getLoanList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET | Loan List Controller");

        PageResponseDTO<LoanDTO> list = loanService.loanList(pageRequestDTO);

        model.addAttribute("list", list);

    }

    // Book Borrow
    @PostMapping("borrow")
    public String postBookBorrow(@RequestBody BookBorrowDTO bookBorrowDTO) {

        log.info("POST | Book Borrow Controller");

        loanService.bookBorrow(bookBorrowDTO);

        return "redirect:/book/read/" + bookBorrowDTO.getBookNumber();
    }

    // Book Return
    @PostMapping("return")
    public ResponseEntity<String> postBookReturn(@RequestBody BookReturnDTO bookReturnDTO) {

        log.info("POST | Book Return Cnntroller");

        loanService.bookReturn(bookReturnDTO);
        
        return ResponseEntity.ok("도서 반납이 완료되었습니다.");

    }

}
