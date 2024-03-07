package org.bs.rental.mapper.member;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTests {

    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Member List Mapper Test
    @Test
    @DisplayName("회원 리스트 매퍼 테스트")
    @Transactional
    public void memberListMapperTest(){

        // Given
        log.info("Member List Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .type("i")
        .keyword("beomsu")
        .build();

        // When
        List<MemberListDTO> list = memberMapper.memberList(pageRequestDTO);

        // Then
        log.info("List: " + list);

        log.info("Member List Mapper Test Complete");
        
    }

    // Member Total Mapper Test
    @Test
    @DisplayName("회원 토탈 매퍼 테스트")
    @Transactional
    public void memberTotalMapperTest(){

        // Given
        log.info("Member Total Mapper Test Start");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        // When
        int result = memberMapper.total(pageRequestDTO);

        // Then
        log.info("result: " + result);

        log.info("Member Total Mapper Test Complete");
    }

    // Member Create Mapper Test
    @Test
    @DisplayName("회원 생성 매퍼 테스트")
    @Transactional
    public void memberCreateMapperTest() {

        // Given
        log.info("Member Create Mapper Test Start");

        String id = "beomsu1221";
        String password = "1234";
        String email = "beomsu_1221@naver.com";
        boolean emailAuth = true;
        String nickname = "beomsu";
        String phoneNumber = "010-7777-7777";
        String birthDate = ("1998-12-21");

        // 객체 생성
        MemberCreateDTO memberCreateDTO = MemberCreateDTO.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .email(email)
                .emailAuth(emailAuth)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .build();

        // When

        // 중복 검사
        if (memberMapper.isIdExists(memberCreateDTO.getId())) {
            throw new DuplicateKeyException("중복된 아이디입니다.");
        }

        if (memberMapper.isEmailExists(memberCreateDTO.getEmail())) {
            throw new DuplicateKeyException("중복된 이메일입니다.");
        }

        if (memberMapper.isNicknameExists(memberCreateDTO.getNickname())) {
            throw new DuplicateKeyException("중복된 닉네임입니다.");
        }

        // 실행
        memberMapper.memberCreate(memberCreateDTO);

        // Then
        MemberDTO member = memberMapper.memberReadOne(id);
        assertAll(
                () -> assertEquals(memberCreateDTO.getId(), member.getId()),
                () -> assertEquals(memberCreateDTO.getPassword(), member.getPassword()),
                () -> assertEquals(memberCreateDTO.getEmail(), member.getEmail()),
                () -> assertEquals(memberCreateDTO.isEmailAuth(), member.isEmailAuth()),
                () -> assertEquals(memberCreateDTO.getNickname(), member.getNickname()),
                () -> assertEquals(memberCreateDTO.getBirthDate(), member.getBirthDate()));

        log.info("Member Create Mapper Test Complete");
    }

    // Member Read One Mapper Test
    @Test
    @DisplayName("회원 정보 조회 매퍼 테스트")
    @Transactional
    public void memberReadOneMapperTest() {

        // Given
        log.info("Member Read One Test Start");
        String id = "admin";

        // When
        MemberDTO memberDTO = memberMapper.memberReadOne(id);

        // Then
        log.info(memberDTO.toString());

        log.info("Member Read One Mapper Test Complete");
    }

    // Member Update Mapper Test
    @Test
    @DisplayName("회원 정보 수정 매퍼 테스트")
    @Transactional
    public void memberUpdateMapperTest() {
        // Given
        log.info("Member Update Mapper Test Start");
        String id = "beomsu";
        String password = "1234";
        String email = "9805121234@naver.com";
        boolean emailAuth = true;
        String phoneNumber = "010-1234-1111";
        String nickname = "beomsu";

        // 객체 생성
        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .email(email)
                .emailAuth(emailAuth)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .build();

        // When
        memberMapper.memberUpdate(memberUpdateDTO);

        // Then
        assertAll(
                () -> assertEquals(id, memberUpdateDTO.getId()),
                () -> assertEquals(email, memberUpdateDTO.getEmail()),
                () -> assertEquals(emailAuth, memberUpdateDTO.isEmailAuth()),
                () -> assertEquals(phoneNumber, memberUpdateDTO.getPhoneNumber()),
                () -> assertEquals(nickname, memberUpdateDTO.getNickname()));

        log.info(memberUpdateDTO.toString());

        log.info("Member Update Mapper Test Complete");
    }

    // Member Delete Mapper Test
    @Test
    @DisplayName("회원 탈퇴 매퍼 테스트")
    @Transactional
    public void memberDeleteMapperTest(){

        // Given
        log.info("Member Delete Mapper Test Start");
        String id = "beomsu";

        // When
        int result = memberMapper.memberDelete(id);

        // Then
        if(result == 1){
            log.info("회원 탈퇴가 정상적으로 되었습니다.");
        } else {
            log.info("회원 탈퇴가 정상적으로 되지 않았습니다.");
        }

        log.info("Member Delete Mapper Test Complete");
    }

    // 이메일 중복 찾기
    @Test
    @DisplayName("이메일 중복 찾기 매퍼 테스트")
    @Transactional
    public void isEmailExistsMapperTest(){

        // Given
        log.info("isEmailExists Mapper Test Start");

        String email = "9805121234@naver.com";

        // When
        boolean result = memberMapper.isEmailExists(email);

        // Then
        log.info("Result: " + result);

        log.info(" isEmailExists Mapper Test Complete");
    }
    
    // 이메일로 아이디 찾기
    @Test
    @DisplayName("이메일로 아이디 찾기 매퍼 테스트")
    @Transactional
    public void findByEmailToIdMapperTest(){

        // Given
        log.info("findByEmailToId Mapper Test Start");

        String email = "9805121234@naver.com";

        // When
        String id = memberMapper.findByEmailToId(email);

        // Then
        log.info("ID: " + id);

        log.info("findByEmailToId Mapper Test Complete");
    }

}
