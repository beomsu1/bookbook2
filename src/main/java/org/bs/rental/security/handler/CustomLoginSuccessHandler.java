package org.bs.rental.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.util.jwt.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("CustomLoginSuccessHandler authentication: " + authentication);

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

        Map<String, Object> claim = memberDTO.getClaims();

        // accessToken 생성
        String accessToken= JWTUtil.generateToken(claim, 60);

        log.info("accessToken: " + accessToken);

        // accessToken 추가
        claim.put("accessToken", accessToken);

        // JSON 데이터로 변환
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claim);

        // 클라이언트에게 JSON 데이터 보내기
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter(); // 현재 HTTP 응답에 대한 PrintWriter 객체 가져오기
        printWriter.println(jsonStr);
        printWriter.close();

    }
}
