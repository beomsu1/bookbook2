package org.bs.rental.mapper.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.rental.dto.book.BookCreateDTO;
import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.book.BookListDTO;
import org.bs.rental.dto.book.BookUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface BookMapper {

    // List
    List<BookListDTO> bookList(PageRequestDTO pageRequestDTO);

    // Total
    int total(PageRequestDTO pageRequestDTO);

    // Book Create
    int bookCreate(BookCreateDTO bookCreateDTO);

    // Book Read
    BookDTO bookRead(Long bookNumber);

    // Book Update
    int bookUpdate(BookUpdateDTO bookUpdateDTO);

    // Book Delete
    int bookDelete(Long bookNumber);
    
}
