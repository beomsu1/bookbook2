package org.bs.rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bs.rental.dto.book.BookListByMemberDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.service.Book.BookService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/book/")

public class BookRestController {

    private final BookService bookService;

    @GetMapping("list/{id}")
    PageResponseDTO<BookListByMemberDTO> getListByMember (PageRequestDTO pageRequestDTO, @PathVariable("id") String id){

        log.info("GET | List By Member Controller Start");

        return bookService.listOfBookBorrowedByMember(pageRequestDTO,id);
    }

}
