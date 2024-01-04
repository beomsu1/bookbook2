package org.bs.rental.dto.book;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private Long book_number;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publication_date;
    private String publisher;
    private String language;
    private int total_pages;
    private String description;
    private String status;
    private List<String> fnames;
}
