package org.bs.rental.service.notice;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.mapper.notice.NoticeImageMapper;
import org.bs.rental.mapper.notice.NoticeMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServcieImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    private final NoticeImageMapper noticeImageMapper;

    // Notice List
    @Override
    public PageResponseDTO<NoticeListDTO> noticeList(PageRequestDTO pageRequestDTO) {

        log.info("Notice List Service Impl Start");

        List<NoticeListDTO> list = noticeMapper.noticeList(pageRequestDTO);
        int total = noticeMapper.noticeTotal(pageRequestDTO);

        return PageResponseDTO.<NoticeListDTO>builder()
                .list(list)
                .total(total)
                .build();
    }

    // Notice Create
    @Override
    public void noticeCreate(NoticeCreateDTO noticeCreateDTO) {

        log.info("Book Create Service Impl Start");

        List<String> fnames = noticeCreateDTO.getFnames();

        // 게시물 등록
        int count = noticeMapper.noticeCreate(noticeCreateDTO);

        if (count > 0 && noticeCreateDTO.getFnames() != null && !noticeCreateDTO.getFnames().isEmpty()) {

            Long nno = noticeCreateDTO.getNno();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "nno", "" + nno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            noticeImageMapper.insertImage(list);

        }

    }

    // Notice Read
    @Override
    public NoticeDTO noticeRead(Long nno) {

        log.info("Notice Read Service Impl Start");

        return noticeMapper.noticeRead(nno);
    }

    // Notice Update
    @Override
    public void noticeUpdate(NoticeUpdateDTO noticeUpdateDTO) {

        log.info("Notice Update Service Impl Start");

        // 수정 업데이트
        int count = noticeMapper.noticeUpdate(noticeUpdateDTO);

        // 기존파일 삭제
        noticeImageMapper.deleteImage(noticeUpdateDTO.getNno());

        List<String> fnames = noticeUpdateDTO.getFnames();

        if (count > 0 && noticeUpdateDTO.getFnames() != null && !noticeUpdateDTO.getFnames().isEmpty()) {

            Long nno = noticeUpdateDTO.getNno();

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fnames.stream().map(str -> {

                String uuid = str.substring(0, 36);

                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "nno", "" + nno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일 등록
            noticeImageMapper.insertImage(list);

        }

    }

    // Notice Delete
    @Override
    public int noticeDelete(Long nno) {

        log.info("Notice Delete Service Impl Start");

        return noticeMapper.noticeDelete(nno);
    }
    
}
