package org.bs.rental.util.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    // 시크릿 키 생성
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // JWT 토큰 생성 로직
    public static String generateToken(Map<String, Object> valueMap, int min) {

        // JWT 토큰 객체 생성
        String jwtToken = Jwts.builder()
                .setHeader(Map.of("typ", "JWT")) // 헤더 설정
                .setClaims(valueMap) // claim 정보 설정
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant())) // 발급 시간 설정
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) // 만료 시간 설정
                .signWith(key) // 시크릿 키 설정
                .compact();

        return jwtToken;
    }

    // JWT 토큰 검증
    public static Map<String, Object> validateToken(String token) {

        Map<String, Object> claim = null;

        try {
            claim = Jwts.parserBuilder()
                    .setSigningKey(key) // 서명 확인
                    .build()
                    .parseClaimsJws(token)// 유효성 검증 실패시 오류 ,  parseClaimsJws, parseClaimsJwt 차이: 키를 이용한 서명 여부
                    .getBody(); // 바디 부분 추출 -> 클레임 정보

        } catch (MalformedJwtException malformedJwtException) {
            throw new JWTException("MalFormed");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JWTException("Expired");
        } catch (InvalidClaimException invalidClaimException) {
            throw new JWTException("Invalid");
        } catch (JwtException jwtException) {
            throw new JWTException("JWTError");
        } catch (Exception e) {
            throw new JWTException("Error");
        }

        return claim;

    }


}
