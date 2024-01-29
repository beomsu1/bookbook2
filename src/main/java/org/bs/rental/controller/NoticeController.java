package org.bs.rental.controller;

import org.bs.rental.dto.notice.NoticeCreateDTO;
import org.bs.rental.dto.notice.NoticeDTO;
import org.bs.rental.dto.notice.NoticeListDTO;
import org.bs.rental.dto.notice.NoticeUpdateDTO;
import org.bs.rental.service.notice.NoticeService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/notice/")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

        private final NoticeService noticeService;

    // Notice List
    @GetMapping("list")
    public void getNoticeList(PageRequestDTO pageRequestDTO, Model model){

        log.info("GET | Notice List Controller");

        PageResponseDTO<NoticeListDTO> list = noticeService.noticeList(pageRequestDTO);

        model.addAttribute("notice", list);

    }

    // Get Notice Create
    @GetMapping("create")
    public void getNoticeCreate(){

        log.info("GET | Notice Create Controller");

    }

    // Get Notice Read
    @GetMapping("read/{nno}")
    public String getNoticeRead(@PathVariable("nno") Long nno, Model model){

        log.info("GET | Notice Read Controller");

        NoticeDTO info = noticeService.noticeRead(nno);

        model.addAttribute("notice", info);

        return "/notice/read";

    }

    // Get Notice Update
    @GetMapping("update/{nno}")
    public String getNoticeUpdade(@PathVariable("nno") Long nno, Model model){

        log.info("GET | Notice Update Controller");

        NoticeDTO info = noticeService.noticeRead(nno);

        model.addAttribute("notice", info);

        return "/notice/update";

    }

    // Post Notice Create
    @PostMapping("create")
    public String postNoticeCreate(NoticeCreateDTO noticeCreateDTO){

        log.info("POST | Notice Create Controller");

        noticeService.noticeCreate(noticeCreateDTO);

        return "redirect:/notice/list";
    }

    // Post Notice Update
    @PostMapping("update")
    public String postNoticeUpdate(NoticeUpdateDTO noticeUpdateDTO){

        log.info("POST | Notice Update Controller");

        noticeService.noticeUpdate(noticeUpdateDTO);

        return "redirect:/notice/read" + noticeUpdateDTO.getNno();

    }

    // Post Notice Delete
    @PostMapping("delete/{nno}")
    public String postNoticeDelete(@PathVariable("nno") Long nno){

        log.info("POST | Notice Delete Controller");

        noticeService.noticeDelete(nno);

        return "redirect:/notice/list";

    }
    
}
