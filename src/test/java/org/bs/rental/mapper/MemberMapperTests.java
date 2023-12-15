package org.bs.rental.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.mapper.member.MemberMapper;
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

    @Test
    @DisplayName("회원 생성 테스트")
    // @Transactional
    public void memberCreateMapperTest() {

        // Given
        log.info("Member Create Mapper Test Start");

        String id = "beomsu1221";
        String password = "1234";
        String email = "beomsu_1221@naver.com";
        boolean emailAuth = true;
        String nickname = "범수";
        String phoneNumber = "010-7777-7777";
        LocalDate birthDate = LocalDate.of(1998, 12, 21);

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

    // Member Read One
    @Test
    @DisplayName("회원 정보 조회 테스트")
    @Transactional
    public void memberReadOneMapperTest() {

        // Given
        log.info("Member Read One Test Start");
        String id = "beomsu1221";

        // When
        MemberDTO memberDTO = memberMapper.memberReadOne(id);

        // Then
        log.info(memberDTO.toString());
        log.info("Member Read One Mapper Test Complete");
    }

    // Member Update
    @Test
    @DisplayName("회원 정보 수정 테스트")
    // @Transactional
    public void memberUpdateMapperTest() {
        // Given
        log.info("Member Update Mapper Test Start");
        String id = "beomsu1221";
        String password = "1234";
        String email = "9805121234@naver.com";
        boolean emailAuth = true;
        String phoneNumber = "010-1234-1111";
        String nickname = "beomsu";

        // 객체 생성
        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.builder()
                .id(id)
                .password(password)
                .email(email)
                .emailAuth(emailAuth)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .build();

        // When

        // id로 조회 후 변경
        MemberDTO memberDTO = memberMapper.memberReadOne(memberUpdateDTO.getId());

        // 중복 검사
        if (memberMapper.isEmailExists(memberUpdateDTO.getEmail())) {
            throw new DuplicateKeyException("중복된 이메일입니다.");
        }

        if (memberMapper.isNicknameExists(memberUpdateDTO.getNickname())) {
            throw new DuplicateKeyException("중복된 닉네임입니다.");
        }

        memberDTO.update(
                memberUpdateDTO.getPassword(),
                memberUpdateDTO.getEmail(),
                memberUpdateDTO.isEmailAuth(),
                memberUpdateDTO.getPhoneNumber(),
                memberUpdateDTO.getNickname());

        // Then
        assertAll(
                () -> assertEquals(id, memberDTO.getId()),
                () -> assertEquals(password, memberDTO.getPassword()),
                () -> assertEquals(email, memberDTO.getEmail()),
                () -> assertEquals(emailAuth, memberDTO.isEmailAuth()),
                () -> assertEquals(phoneNumber, memberDTO.getPhoneNumber()),
                () -> assertEquals(nickname, memberDTO.getNickname()));

        log.info(memberDTO.toString());

        log.info("Member Update Mapper Test Complete");
    }

}
