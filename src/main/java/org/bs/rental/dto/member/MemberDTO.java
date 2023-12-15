package org.bs.rental.dto.member;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Data
public class MemberDTO extends User {

    private String id;
    private String password;
    private String email;
    private boolean emailAuth;
    private String phoneNumber;
    private String nickname;
    private LocalDate birthDate;
    private LocalDateTime accountDate;
    private String role;

    // 부모 생성자 호출
    public MemberDTO(String id, String password, String email, boolean emailAuth, String phoneNumber,
                     String nickname, LocalDate birthDate, LocalDateTime accountDate, String role) {
        super(id, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role)));

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

    // 변경 메소드
    public void update(String newPassword, String newEmail, boolean newEmailAuth, String newPhoneNumber, String newNickname) {
        if (newPassword != null) {
            this.password = newPassword;
        }
        if (newEmail != null) {
            this.email = newEmail;
        }
        if (newEmailAuth) {
            this.emailAuth = newEmailAuth;
        }
        if (newPhoneNumber != null) {
            this.phoneNumber = newPhoneNumber;
        }
        if (newNickname != null) {
            this.nickname = newNickname;
        }
    }

}
