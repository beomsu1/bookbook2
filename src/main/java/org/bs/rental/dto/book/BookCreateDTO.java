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
public class BookCreateDTO {

    private Long bookNumber;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String publisher;
    private String language;
    private String totalPages;
    private String description;
    private List<String> fnames;

}
