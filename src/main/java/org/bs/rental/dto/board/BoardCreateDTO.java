package org.bs.rental.dto.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCreateDTO {

    private Long tno;
    private String title;
    private String content;
    private String writer;
    private List<String> fnames;
    
}
