package org.bs.rental.service.Member;

import org.bs.rental.dto.member.MemberCreateDTO;
import org.bs.rental.dto.member.MemberDTO;
import org.bs.rental.dto.member.MemberListDTO;
import org.bs.rental.dto.member.MemberUpdateDTO;
import org.bs.rental.util.page.PageRequestDTO;
import org.bs.rental.util.page.PageResponseDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    // List
    PageResponseDTO<MemberListDTO> memberList(PageRequestDTO pageRequsetDTO);

    // Create
    int memberCreate(MemberCreateDTO memberCreateDTO);

    // Read
    MemberDTO memberReadOne(@Param("id") String id);

    // Update
    int memberUpdate(MemberUpdateDTO memberUpdateDTO);

    // Delete
    int memberDelete(@Param("id") String id);

    // 이메일 중복 찾기
    Boolean isEmailExists(String email);

    // 이메일로 id 찾기
    String findByEmailToId(@Param("email") String email);
    
}
