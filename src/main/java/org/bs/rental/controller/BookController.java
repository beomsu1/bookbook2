package org.bs.rental.controller;

import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.dto.loan.LoanReadDTO;
import org.bs.rental.service.Book.BookService;
import org.bs.rental.service.Loan.LoanService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book/")
@EnableMethodSecurity
public class BookController {

    private final BookService bookService;

    private final LoanService loanService;

    // Book List
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void getBookList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET | Book List Controller");

        PageResponseDTO<BookListDTO> list = bookService.bookList(pageRequestDTO);

        model.addAttribute("book", list);
    }

    // Get Book Create
    @GetMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void getBookCreate() {

        log.info("GET | Book Create Controller");

    }

    // Get Book Read
    @GetMapping("read/{bookNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getBookRead(@PathVariable("bookNumber") Long bookNumber, Model model) {

        log.info("GET | Book Read Controller");

        BookDTO list = bookService.bookRead(bookNumber);

        LoanReadDTO loan = loanService.borrowedByBookNumber(bookNumber);

        model.addAttribute("book", list);
        model.addAttribute("loan", loan);

        return "book/read";
    }

    // Get Book Update
    @GetMapping("update/{bookNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getBookUpdate(@PathVariable("bookNumber") Long bookNumber, Model model) {

        log.info("GET | Book Update Controller");

        BookDTO list = bookService.bookRead(bookNumber);

        model.addAttribute("book", list);

        return "book/update";

    }

    // Post Book Create
    @PostMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBookCreate(BookCreateDTO bookCreateDTO) {

        log.info("POST | Book Create Controller");

        bookService.bookCreate(bookCreateDTO);

        return "redirect:/book/list";
    }

    // Post Book Update
    @PostMapping("update/{bookNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBookUpdate(BookUpdateDTO bookUpdateDTO) {

        log.info("POST | Book Update Controller");

        bookService.bookUpdate(bookUpdateDTO);

        return "redirect:/book/read/" + bookUpdateDTO.getBookNumber();
    }

    // Post Book Delete
    @PostMapping("delete/{bookNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postBookDelete(@PathVariable("bookNumber") Long bookNumber) {

        log.info("POST | Book Delete Controller");

        bookService.bookDelete(bookNumber);

        return "redirect:/book/list";
    }

}
