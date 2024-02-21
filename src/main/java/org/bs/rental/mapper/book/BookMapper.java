package org.bs.rental.mapper.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.bs.rental.dto.book.*;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface BookMapper {

    // List
    List<BookListDTO> bookList(PageRequestDTO pageRequestDTO);

    // Total
    int bookTotal(PageRequestDTO pageRequestDTO);

    // Book Create
    int bookCreate(BookCreateDTO bookCreateDTO);

    // Book Read
    BookDTO bookRead(Long bookNumber);

    // Book Update
    int bookUpdate(BookUpdateDTO bookUpdateDTO);

    // Book Delete
    int bookDelete(Long bookNumber);

    // Book Status To Available
    @Update("UPDATE book SET status = '대출 가능' WHERE book_number = #{bookNumber}")
    int bookStatusToAvailable(@Param("bookNumber") Long bookNumber);

    // Book Status To Borrow
    @Update("UPDATE book SET status = '대출 중' WHERE book_number = #{bookNumber}")
    int bookStatusToBorrowed(@Param("bookNumber") Long bookNumber);

    // 회원이 대여한 책 리스트
    List<BookListByMemberDTO> listOfBookBorrowedByMember(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO, @Param("id") String id);

    // 회원이 반납한 책 리스트
    List<BookListByMemberDTO> listOfBookReturnByMember(@Param("pageRequestDTO") PageRequestDTO pageRequestDTO, @Param("id") String id);


}
