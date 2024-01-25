package org.bs.rental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;

    // 자동 로그인
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();

        repo.setDataSource(dataSource);

        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("-----------------SecurityFilterChain-----------------");

        http.formLogin(config -> {

            config.loginPage("/member/login")

                    .defaultSuccessUrl("/book/list", true);

        });

        // 세션 보호
        http.sessionManagement(config -> {
            config.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .sessionFixation().migrateSession();
        });


        // csrf 토큰 비활성화
        http.csrf(config -> {
            config.disable();
        });

        // 자동 로그인
        http.rememberMe(config -> {

            config.tokenRepository(persistentTokenRepository());

            config.tokenValiditySeconds(60*60);
        });

        return http.build();
    }

}
