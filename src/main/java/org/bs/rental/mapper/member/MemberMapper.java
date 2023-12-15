package org.bs.rental.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.util.page.PageRequsetDTO;
import org.springframework.data.repository.query.Param;

@Mapper
public interface MemberMapper {

    // List
    List<MemberDTO> memberList (PageRequsetDTO pageRequsetDTO);

    // Create
    int memberCreate(MemberCreateDTO memberCreateDTO);

    // Read
    MemberDTO memberReadOne(@Param("id") String id);

    // Update
    int memberUpdate(MemberUpdateDTO memberUpdateDTO);

    // Delete
    int memberDelete(@Param("id") String id);

    // id 중복 검사
    boolean isIdExists(@Param("id") String id);

    // email 중복 검사
    boolean isEmailExists(@Param("email") String email);

    // nickname 중복 검사
    boolean isNicknameExists(@Param("nickname") String nickname);

}
