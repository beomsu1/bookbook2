package org.bs.rental.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Transactional
public interface EmailService {

    // 랜덤 코드 생성
    String createdCode();

    // 내용 설정
    String setContext(String code);

    // 메일 반환
    MimeMessage createEmailForm(String email) throws MessagingException;

    // 메일 보내기
    void sendEmail(String toEmail) throws MessagingException;

    // 코드 검증
    Boolean verifyEmailCode(String email, String code);

    // 고유 회원ID 생성
    String makeMemberId(String email) throws NoSuchAlgorithmException;
    
}
