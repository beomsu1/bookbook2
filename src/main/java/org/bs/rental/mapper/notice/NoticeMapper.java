package org.bs.rental.mapper.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;

@Mapper
public interface NoticeMapper {

    // Notice List
    List<NoticeListDTO> noticeList(PageRequestDTO pageRequestDTO);

    // Notice Total
    int noticeTotal (PageRequestDTO pageRequestDTO);

    // Notice Create
    int noticeCreate(NoticeCreateDTO noticeCreateDTO);

    // Notice Read
    NoticeDTO noticeRead(Long nno);

    // Notice Update
    int noticeUpdate(NoticeUpdateDTO noticeUpdateDTO);

    // Notice Delete
    int noticeDelete(Long nno);
    
}
