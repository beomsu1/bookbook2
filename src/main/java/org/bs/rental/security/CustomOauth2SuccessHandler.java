package org.bs.rental.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bs.rental.dto.member.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomOauth2SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

        if(memberDTO.getPassword() == null || memberDTO.getPassword().equals("")){

            response.sendRedirect("/member/kakao/create");

        } else {
            response.sendRedirect("/book/list");
        }
    }
}
