package org.bs.rental.dto.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberReadDTO {

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
