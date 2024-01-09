package org.bs.rental.dto.member;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class MemberDTO extends User {

    private String id;
    private String password;
    private String email;
    private boolean emailAuth;
    private String phoneNumber;
    private String nickname;
    private String birthDate;
    private LocalDateTime accountDate;
    private String role;

    public MemberDTO(String id, String password, String email, boolean emailAuth,
            String phoneNumber, String nickname, String birthDate,
            LocalDateTime accountDate, String role) {
        super(email, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));

        this.id = id;
        this.password = password;
        this.email = email;
        this.emailAuth = emailAuth;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.accountDate = accountDate;
        this.role = role;
    }

}
