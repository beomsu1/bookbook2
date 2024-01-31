package org.bs.rental.dto.reply.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyUpdateDTO {

    private Long rno;
    private Long tno;
    private String reply;
    private String replyer;

    @Builder.Default
    private Long gno = 0L;

    private boolean status;
}
