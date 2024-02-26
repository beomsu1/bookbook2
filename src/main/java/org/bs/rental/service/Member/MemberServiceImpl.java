package org.bs.rental.service.Member;

import java.util.List;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.mapper.member.MemberMapper;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    // 회원 리스트
    @Override
    public PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequsetDTO) {

        log.info("Member List Service Impl Start");

        int total = memberMapper.total(pageRequsetDTO);
        List<MemberListDTO> list = memberMapper.memberList(pageRequsetDTO);

        return PageResponseDTO.<MemberListDTO>builder()
                .total(total)
                .list(list)
                .build();
    }

    // 회원 가입
    @Override
    public int memberCreate(MemberCreateDTO memberCreateDTO) {

        log.info("Member Create Service Impl Start");

        if (memberMapper.isIdExists(memberCreateDTO.getId())) {
            throw new DuplicateKeyException("중복된 아이디입니다.");
        }

        if (memberMapper.isEmailExists(memberCreateDTO.getEmail())) {
            throw new DuplicateKeyException("중복된 이메일입니다.");
        }

        if (memberMapper.isNicknameExists(memberCreateDTO.getNickname())) {
            throw new DuplicateKeyException("중복된 닉네임입니다.");
        }

        return memberMapper.memberCreate(MemberCreateDTO.builder()
        .id(memberCreateDTO.getId())
        .password(passwordEncoder.encode(memberCreateDTO.getPassword()))
        .email(memberCreateDTO.getEmail())
        .emailAuth(memberCreateDTO.isEmailAuth())
        .phoneNumber(memberCreateDTO.getPhoneNumber())
        .nickname(memberCreateDTO.getNickname())
        .birthDate(memberCreateDTO.getBirthDate())
        .build());
    }

    // 회원 정보
    @Override
    public MemberDTO memberReadOne(String id) {

        log.info("Member Read One Service Impl Start");

        return memberMapper.memberReadOne(id);
    }

    // 회원 정보 수정
    @Override
    public int memberUpdate(MemberUpdateDTO memberUpdateDTO) {

        log.info("Member Update Service Impl Start");

        if (memberMapper.isEmailExists(memberUpdateDTO.getEmail())) {
            throw new DuplicateKeyException("중복된 이메일입니다.");
        }

        if (memberMapper.isNicknameExists(memberUpdateDTO.getNickname())) {
            throw new DuplicateKeyException("중복된 닉네임입니다.");
        }

        return memberMapper.memberUpdate(MemberUpdateDTO.builder()
                        .id(memberUpdateDTO.getId())
                        .password(passwordEncoder.encode(memberUpdateDTO.getPassword()))
                        .phoneNumber(memberUpdateDTO.getPhoneNumber())
                        .email(memberUpdateDTO.getEmail())
                        .emailAuth(memberUpdateDTO.isEmailAuth())
                        .nickname(memberUpdateDTO.getNickname())
                .build());
    }

    // 회원 탈퇴
    @Override
    public int memberDelete(String id) {

        log.info("Member Delete Service Impl Start");

        return memberMapper.memberDelete(id);
    }

    // 이메일 중복 찾기
    @Override
    public Boolean isEmailExists(String email) {

        log.info("isEmailExists Service Impl Start");

        return memberMapper.isEmailExists(email);
    }

    @Override
    public String findByEmailToId(String email) {

        log.info("findByEmailToId Service Impl Start");

        return memberMapper.findByEmailToId(email);
    }

}
