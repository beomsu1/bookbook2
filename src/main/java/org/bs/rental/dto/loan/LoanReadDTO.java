package org.bs.rental.dto.loan;

import lombok.Data;

@Data
public class LoanReadDTO {

    private Long book_number;
    private String member_id;
    private String status;
    
}
