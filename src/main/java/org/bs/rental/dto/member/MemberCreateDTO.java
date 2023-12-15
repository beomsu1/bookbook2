package org.bs.rental.dto.member;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateDTO {

    private String id;
    private String password;
    private String email;
    private boolean emailAuth;
    private String phoneNumber;
    private String nickname;
    private LocalDate birthDate;
    
}
