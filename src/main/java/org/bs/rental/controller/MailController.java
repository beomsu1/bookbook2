package org.bs.rental.controller;

import org.bs.rental.dto.mail.MailAuthDTO;
import org.bs.rental.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail/")
@Log4j2
public class MailController {

    private final MailService emailService;

    // 이메일 발송
    @GetMapping("{email}/authcode")
    public ResponseEntity<String> getSendEmail(@PathVariable("email") String email) throws MessagingException {

        emailService.sendEmail(email);

        return ResponseEntity.ok("이메일을 확인하세요.");

    }

    // 인증 코드 처리
    @PostMapping("{email}/authcode")
    public ResponseEntity<String> postVerifyEmailCode(@PathVariable("email") String email, @RequestBody MailAuthDTO mailAuthDTO) {

        log.info(email);
        log.info(mailAuthDTO.getCode());

        if (emailService.verifyEmailCode(email, mailAuthDTO.getCode())) {
            return ResponseEntity.ok("true");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code");
    }

}
