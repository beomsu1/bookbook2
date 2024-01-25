package org.bs.rental.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListDTO {

    private Long tno;
    private String title;
    private String writer;
    private LocalDateTime createDate;
    private List<String> fnames;
    
}
