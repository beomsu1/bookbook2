package org.bs.rental.dto.member;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberDTO  {

    private String id;
    private String password;
    private String email;
    private boolean emailAuth;
    private String phoneNumber;
    private String nickname;
    private LocalDate birthDate;
    private LocalDateTime accountDate;
    private String role;

}
