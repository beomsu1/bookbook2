package org.bs.rental.dto.loan;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {

    private Long lno;
    private Long book_number;
    private String member_id;
    private LocalDate start_date;
    private LocalDate due_date;
    private LocalDate return_date;
    private String status;
    
}
