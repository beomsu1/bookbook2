package org.bs.rental.dto.member;

import lombok.Data;

@Data
public class MemberListDTO {

    private String id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String birthDate;
    private String accountDate;
    
}
