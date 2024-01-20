package org.bs.rental.dto.loan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookBorrowDTO {

    private Long bookNumber;
    private String memberId;
    private LocalDateTime dueDate;
    private int selectedPeriod;
    
    // 선택한 기간에 따라 dueDate 설정
    public void setDueDateFromSelectedPeriod(int selectedPeriod) {
        switch (selectedPeriod) {
            case 1:
                this.dueDate = LocalDateTime.now().plus(1, ChronoUnit.WEEKS);
                break;
            case 2:
                this.dueDate = LocalDateTime.now().plus(2, ChronoUnit.WEEKS);
                break;
            // 기타 선택지에 대한 처리도 추가 가능
            default:
                throw new IllegalArgumentException("Invalid selected period: " + selectedPeriod);
        }
    }
}
