package org.bs.rental.dto.book;

import java.util.Date;

import lombok.Data;

@Data
public class BookListDTO {

    private long bookNumber;
    private String title;
    private String author;
    private Date publicationDate;
    private String publisher;
    private String language;
    private int totalPages;
    private String thumbnailUrl;
    private String bookStatus; // 대출 상태
    
}
