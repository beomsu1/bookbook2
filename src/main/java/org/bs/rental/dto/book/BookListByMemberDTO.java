package org.bs.rental.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListByMemberDTO {

    private Long lno;
    private Long bookNumber;
    private String title;
    private String author;
    private LocalDateTime startDate;
    private LocalDateTime returnDate;
    private String status;
}
