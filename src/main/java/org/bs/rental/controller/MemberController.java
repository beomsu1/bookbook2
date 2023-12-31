package org.bs.rental.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.service.Member.MemberService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    // GET Member Login
    @GetMapping("login")
    public void getMemberLogin() {

        log.info("GET | Member Login Controller");
    }

    // GET Member List
    @GetMapping("list")
    public void getMemberList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET | Member List Controller");

        PageResponseDTO<MemberListDTO> list = memberService.memberList(pageRequestDTO);

        model.addAttribute("member", list);

    }

    // GET Member Create
    @GetMapping("create")
    public void getMemberCreate() {

        log.info("GET | Member Create Controller");

    }

    // GET Member Read One
    @GetMapping("read/{id}")
    public String getMemberReadOne(@PathVariable("id") String id, Model model) {

        log.info("GET | Member Read One Controller");

        MemberDTO info = memberService.memberReadOne(id);

        model.addAttribute("member", info);

        return "/member/read";

    }

    // GET Member Update
    @GetMapping("update/{id}")
    public String getMemberUpdate(@PathVariable("id") String id, Model model) {

        log.info("GET | Member Update Controller");

        MemberDTO info = memberService.memberReadOne(id);

        model.addAttribute("member", info);

        return "/member/update";

    }

    // POST Member Create
    @PostMapping("create")
    public String postMemberCreate(MemberCreateDTO memberCreateDTO) {

        log.info("POST | Member Create Controller");


        memberService.memberCreate(memberCreateDTO);

        return "redirect:/member/login";

    }

    // POST Member Update
    @PostMapping("update")
    public String postMemberUpdate(MemberUpdateDTO memberUpdateDTO) {

        log.info("POST | Member Update Controller");

        memberService.memberUpdate(memberUpdateDTO);

        return "redirect:/member/read" + memberUpdateDTO.getId();

    }

    // POST Member Delete
    @PostMapping("delete/{id}")
    public String postMemberDelete(@PathVariable("id") String id) {

        log.info("POST | Member Delete Controller");

        memberService.memberDelete(id);

        return "redirect:/member/login";

    }

    // POST Member Logout
    @PostMapping("logout")
    public String postMemberLogout(HttpServletRequest request, HttpServletResponse response) {

        log.info("POST | Member Logout Controller");

        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // 자동 로그인 토큰 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember-me")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return "redirect:/member/login";
    }

}
