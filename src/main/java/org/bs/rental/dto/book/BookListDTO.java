package org.bs.rental.dto.book;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BookListDTO {

    private long book_number;
    private String title;
    private String author;
    private Date publication_date;
    private String publisher;
    private String language;
    private String total_pages;
    private String status;
    private List<String> fnames;
    
}
