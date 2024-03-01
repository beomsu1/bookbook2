package org.bs.rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bs.rental.dto.book.BookListByMemberDTO;
import org.bs.rental.service.Book.BookService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/book/")
@EnableMethodSecurity
public class BookRestController {

    private final BookService bookService;

    // 회원이 대여한 책 리스트 컨트롤러
    @GetMapping("borrowList/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    PageResponseDTO<BookListByMemberDTO> getBorrowListByMember (PageRequestDTO pageRequestDTO, @PathVariable("id") String id){

        log.info("GET | Borrow List By Member Controller Start");

        return bookService.listOfBookBorrowedByMember(pageRequestDTO,id);
    }

    // 회원이 반납한 책 리스트 컨트롤러
    @GetMapping("returnList/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    PageResponseDTO<BookListByMemberDTO> getReturnListByMember (PageRequestDTO pageRequestDTO, @PathVariable("id") String id){

        log.info("GET | Return List By Member Controller Start");

        return  bookService.listOfBookReturnByMember(pageRequestDTO,id);
    }

}
