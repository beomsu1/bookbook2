package org.bs.rental.dto.book;

import java.util.Date;

import lombok.Data;

@Data
public class BookListDTO {

    private long book_number;
    private String title;
    private String author;
    private Date publicationDate;
    private String publisher;
    private String language;
    private int total_pages;
    private String thumbnail_url;
    private String bookStatus; // 대출 상태
    
}
