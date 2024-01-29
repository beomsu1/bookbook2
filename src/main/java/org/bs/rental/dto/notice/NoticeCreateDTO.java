package org.bs.rental.dto.notice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeCreateDTO {

    private Long nno;
    private String title;
    private String content;
    private String writer;
    private List<String> fnames;
    
}
