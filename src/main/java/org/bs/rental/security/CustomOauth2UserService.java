package org.bs.rental.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.service.Member.MemberService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 클라이언트 정보
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        String ClientName = clientRegistration.getClientName();

        // OAuth2User: 사용자의 정보를 포함하고 있는 객체
        OAuth2User oAuth2User = super.loadUser(userRequest); // super.(부모 클래스 호출) X -> 재귀적 호출로 스택 오버플로우 발생

        // 속성 가져오기
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        // ClientName / kakao, naver, google
        switch (ClientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        boolean result = memberService.isEmailExists(email);

        // 가입된 이메일이 있을 떄
        if (result) {

            String id = memberService.findByEmailToId(email);

            return memberService.memberReadOne(id);

        } else {

            MemberDTO memberDTO = new MemberDTO(email, "", email, true, "", "", "", LocalDateTime.now(), "USER");

            return memberDTO;

        }
    }

    //
    private String getKakaoEmail(Map<String, Object> paramMap) {

        // 계정 정보 가져오기
        Object value = paramMap.get("kakao_account");

        Map accountMap = (Map) value;

        String email = (String) accountMap.get("email");

        log.info("Email: " + email);

        return email;
    }
}
