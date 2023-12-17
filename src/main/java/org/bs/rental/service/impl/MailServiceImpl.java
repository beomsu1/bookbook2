package org.bs.rental.service.impl;

import java.util.Random;

import org.bs.rental.service.MailService;
import org.bs.rental.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String configEmail;

    // 랜덤 코드 생성
    @Override
    public String createdCode() {
        int asciiNumber = 48; // 숫자 '0'
        int asciiAlphabet = 122; // 알파벳 'z'
        int targetStringLength = 6;
        Random random = new Random();
        
        return random.ints(asciiNumber, asciiAlphabet + 1)
                .filter(i -> (i >= 48 && i <= 57) || (i >= 65 && i <= 90) || (i >= 97 && i <= 122))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // Thymeleaf 템플릿 엔진을 사용하여 동적으로 메일 내용을 생성
    @Override
    public String setContext(String code) {

        // 변수와 템플릿 처리하는 컨텍스트 생성
        Context context = new Context();

        // 템플릿 처리하기 위한 엔진 생성
        TemplateEngine templateEngine = new TemplateEngine();

        // 템플릿 파일의 위치 및 속성을 정의하는 리졸버 생성
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code); // 변수에 매개변수로 들어온 code의 값 저장

        templateResolver.setPrefix("templates/mail/"); // 템플릿 파일 위치 지정
        templateResolver.setSuffix(".html"); // 확장자 지정
        templateResolver.setTemplateMode(TemplateMode.HTML); // 템플릿 모드 HTML 지정
        templateResolver.setCacheable(false); // 템플릿 캐시 사용 X
        templateResolver.setCharacterEncoding("UTF-8"); // UTF-8 설정

        templateEngine.setTemplateResolver(templateResolver); // 템플릿 엔진에 리졸버 설정

        return templateEngine.process("mail", context); // 메일 내용 문자열 반환
    }

    // 메일 반환
    @Override
    public MimeMessage createEmailForm(String email) throws MessagingException {

        String authcode = createdCode();

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); // 수신자 설정
        message.setSubject("인증번호입니다.");
        message.setFrom(configEmail);
        message.setText(setContext(authcode), "UTF-8", "html");

        redisUtil.setDataExpire(email, authcode, 60 * 5);

        return message;
    }

    // 메일 보내기
    @Override
    public void sendEmail(String toEmail) throws MessagingException {

        // 데이터 삭제
        if(redisUtil.existData(toEmail)){
            redisUtil.deleteData(toEmail);
        }

        MimeMessage message = createEmailForm(toEmail);

        mailSender.send(message);
    }

    // 코드 검증
    @Override
    public Boolean verifyEmailCode(String email, String code) {
        
        // Redis에서 해당 이메일 코드 수집
        String codeFoundByEmail = redisUtil.getDate(email);
        log.info(codeFoundByEmail);

        if(codeFoundByEmail == null){
            return false;
        }
        
        log.info(codeFoundByEmail.equals(code));

        return codeFoundByEmail.equals(code);
    }

}
