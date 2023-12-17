package org.bs.rental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MailServiceTests {

    @Autowired
    private MailService emailService;

    // Create Code
    @Test
    @DisplayName("랜덤 코드 생성 테스트")
    @Transactional
    public void createCodeServcieTest(){

        // Given
        log.info("Create Code Service Test Start");

        // When
        String code = emailService.createdCode();

        // Then
        log.info(code);

        log.info("Create Code Test Complte");
    }

    // Set Context
    @Test
    @DisplayName("내용 설정 테스트")
    @Transactional
    public void setContextServiceTest(){

        // Given
        log.info("Set Context Service Test Start");

        String code = emailService.createdCode();

        // When
        String context = emailService.setContext(code);

        // Then
        log.info(context);

        log.info("Set Context Service Test Complete");
    }


    // Create Email Form
    @Test
    @DisplayName("메일 반환 테스트")
    @Transactional
    public void createEmailFormServiceTest() throws MessagingException{

        // Given
        log.info("Create Email Form Service Test Start");

        String email = "beomsu_1221@naver.com";

        // When
       MimeMessage message = emailService.createEmailForm(email);

        // Then
        log.info(message);

        log.info("Create Email Form Service Test Complete");
    }

    // Send Email
    @Test
    @DisplayName("메일 발송 테스트")
    @Transactional
    public void sendEmailServiceTest() throws MessagingException{

        // Given
        log.info("Send Email Service Test Start");

        String email = "beomsu_1221@naver.com";

        // When
        emailService.sendEmail(email);
  
        // Then
        log.info("Send Email Service Test Complete");
  
    }

    // Verify Email Code
    @Test
    @DisplayName("코드 검증 테스트")
    @Transactional
    public void verifyEmailCodeServiceTest() {

        // Given
        log.info("Verify Email Code Service Test Start");

        String email = "beomsu_1221@naver.com";
        String validCode = "DhyCH1"; // redis에 저장된 값

        // When
        Boolean result = emailService.verifyEmailCode(email, validCode);

        // Then
        assertEquals(true, result, "유효한 코드입니다.");

        log.info("Verify Email Code Service Test Complete");

    }
    
}
