package org.bs.rental.security.filter;

import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import org.bs.rental.util.jwt.JWTUtil;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.util.WebUtils;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
    private final String COOKIE_NAME = "accessToken";

    // 해당 요청에 필터를 적용할지 여부를 결정하는 메서드
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // 프리플라이트 필터 설정 - 브라우저가 본 요청을 보내기 전에 서버에게 허용된 메서드 및 헤더를 확인하는 CORS 메커니즘의 일부
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String uri = request.getRequestURI();

        // login, oauth2 필터 제외
        if (uri.startsWith("/member/login") || uri.startsWith("/oauth2") || uri.startsWith("/css")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = extractAccessTokenFromCookie(request);

        log.info("------------------------------------");
        log.info("accessToken: " + accessToken);
        log.info("------------------------------------");

        try {

            JWTUtil.validateToken(accessToken); // 엑세스 토큰 검사

            filterChain.doFilter(request, response); // 실행

        } catch (Exception e) {

            log.error("JWTCheckFilter Error");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            // JSON 보내기
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }

    }

    // 쿠키에 엑세스 토큰 값 가져오기
    private String extractAccessTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, COOKIE_NAME);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }
}
