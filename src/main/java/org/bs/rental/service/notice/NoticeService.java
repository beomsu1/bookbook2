package org.bs.rental.service.notice;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    // Notice List
    PageResponseDTO<NoticeListDTO> noticeList(PageRequestDTO pageRequestDTO);

    // Notice Create
    void noticeCreate(NoticeCreateDTO noticeCreateDTO);

    // Notice Read
    NoticeDTO noticeRead(Long nno);

    // Notice Update
    void noticeUpdate(NoticeUpdateDTO noticeUpdateDTO);

    // Notice Delete
    int noticeDelete(Long nno);
    
}
