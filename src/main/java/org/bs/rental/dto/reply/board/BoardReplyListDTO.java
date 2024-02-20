package org.bs.rental.dto.reply.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyListDTO {

    private Long rno;
    private Long tno;
    private String reply;
    private String replyer;
    private String nickname;
    private LocalDateTime replyDate;

    // 대댓글
    @Builder.Default
    private Long gno = 0L;
    private int step;

    private boolean status; // 삭제 여부
}
