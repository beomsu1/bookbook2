package org.bs.rental.controller;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.service.Member.MemberService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    // GET Member Login
    @GetMapping("login")
    @PreAuthorize("permitAll()")
    public void getMemberLogin() {

        log.info("GET | Member Login Controller");
    }

    // GET Member List
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void getMemberList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("GET | Member List Controller");

        PageResponseDTO<MemberListDTO> list = memberService.memberList(pageRequestDTO);

        model.addAttribute("member", list);

    }

    // GET Member Create
    @GetMapping("create")
    @PreAuthorize("permitAll()")
    public void getMemberCreate() {

        log.info("GET | Member Create Controller");

    }

    // GET Member Read One
    @GetMapping("read")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getMemberReadOne(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        log.info("GET | Member Read One Controller");

        model.addAttribute("member", userDetails);

        return "/member/read";

    }

    // GET Member Update
    @GetMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void getMemberUpdate(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        log.info("GET | Member Update Controller");

        model.addAttribute("member", userDetails);

    }

    // POST Member Create
    @PostMapping("create")
    @PreAuthorize("permitAll()")
    public String postMemberCreate(MemberCreateDTO memberCreateDTO) {

        log.info("POST | Member Create Controller");

        memberService.memberCreate(memberCreateDTO);

        return "redirect:/member/login";

    }

    // POST Member Update
    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postMemberUpdate(MemberUpdateDTO memberUpdateDTO) {

        log.info("POST | Member Update Controller");

        memberService.memberUpdate(memberUpdateDTO);

        return "redirect:/member/read";

    }

    // POST Member Delete
    @PostMapping("delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postMemberDelete(String id) {

        log.info("POST | Member Delete Controller");

        memberService.memberDelete(id);

        return "redirect:/member/login";

    }

    // POST Member Logout
    @PostMapping("logout")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String postMemberLogout(HttpServletRequest request, HttpServletResponse response) {

        log.info("POST | Member Logout Controller");

        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // 자동 로그인 쿠키, 엑세스 토큰 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember-me"))  {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("accessToken"))  {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        return "redirect:/member/login";
    }

    // GET Member Update
    @GetMapping("kakao/create")
    @PreAuthorize("permitAll()")
    public void getKakaoMemberCreate(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        log.info("GET | Kakao Member Create Controller");

        model.addAttribute("member", userDetails);

    }

    // POST Kakao Member Create
    @PostMapping("kakao/create")
    @PreAuthorize("permitAll()")
    public String postKakaoMemberCreate(MemberCreateDTO memberCreateDTO) {

        log.info("POST | kakao Member Create Controller");

        memberService.memberCreate(memberCreateDTO);

        return "redirect:/member/read";

    }

}
