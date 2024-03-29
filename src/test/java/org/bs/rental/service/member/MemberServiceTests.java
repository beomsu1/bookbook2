package org.bs.rental.service.member;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.service.Member.MemberService;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    // Member List Service Test
    @Test
    @DisplayName("회원 리스트 서비스 테스트")
    // @Transactional
    public void memberListServiceTest() {

        // Given
        log.info("Member List Service Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        PageResponseDTO<MemberListDTO> list = memberService.memberList(pageRequestDTO);

        // Then
        log.info(list);

        log.info("Member List Service Test Complete");

    }

    // Member Create Service Test
    @Test
    @DisplayName("회원 생성 서비스 테스트")
    @Transactional
    public void memberCreateServiceTest() {

        // Given
        log.info("Member Create Service Test Start");

        MemberCreateDTO memberCreateDTO = MemberCreateDTO.builder()
        .id("user1")
        .password("1234")
        .email("user1@naver.com")
        .emailAuth(true)
        .phoneNumber("010-1234-1234")
        .nickname("master")
        .birthDate("1998-12-21")
        .build();

        // When
        memberService.memberCreate(memberCreateDTO);

        // Then
        log.info("Member Create Service Test Complete");

    }

    // Member Read One Serivce Test
    @Test
    @DisplayName("회원 조회 서비스 테스트")
    @Transactional
    public void memberReadOneServiceTest() {

        // Given
        log.info("Member Read One Setvice Test Start");

        String id = "beomsu";

        // When
        MemberDTO read = memberService.memberReadOne(id);

        // Then
        log.info("Information: " + read);

        log.info("Member Read One Service Test Complete");
    }

    // Member Update Service Test
    @Test
    @DisplayName("회원 수정 서비스 테스트")
    @Transactional
    public void memberUpdateServiceTest() {

        // Given
        log.info("Member Update Service Test Start");

        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
        .id("beomsu")
        .password("qwe123")
        .email("user1@naver.com")
        .emailAuth(true)
        .nickname("master1")
        .phoneNumber("010-1234-1111")
        .build();

        // When
        memberService.memberUpdate(memberUpdateDTO);

        // Then
        log.info("Member Update Service Test Complete");

    }

    // Member Delete Service Test
    @Test
    @DisplayName("회원 삭제 서비스 테스트")
    @Transactional
    public void memberDeleteServiceTest() {

        // Given
        log.info("Member Delete Service Test Start");

        String id = "beomsu";

        // When
        memberService.memberDelete(id);

        // Then
        log.info("Member Delete Service Test Complete");
    }
    
    // 이메일 중복 찾기
    @Test
    @DisplayName("이메일 중복 찾기 서비스 테스트")
    @Transactional
    public void isEmailExistsServiceTest(){

        // Given
        log.info("isEmailExists Service Test Start");

        String email = "9805121234@naver.com";

        // When
        boolean result = memberService.isEmailExists(email);

        // Then
        log.info("Result: " + result);

        log.info(" isEmailExists Service Test Complete");
    }
    
    // 이메일로 아이디 찾기
    @Test
    @DisplayName("이메일로 아이디 찾기")
    @Transactional
    public void findByEmailToIdServiceTest(){

        // Given
        log.info("findByEmailToId Service Test Start");

        String email = "9805121234@naver.com";

        // When
        String id = memberService.findByEmailToId(email);

        // Then
        log.info("ID: " + id);

        log.info("findByEmailToId Service Test Complete");

    }

}
