package org.bs.rental.dto.naver;

import java.util.List;

import org.bs.rental.dto.book.BookDTO;

import lombok.Data;

@Data
public class NaverApiResponseDTO {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookDTO> items;
    
}
