package org.bs.rental.config;

import org.bs.rental.security.filter.JWTCheckFilter;
import org.bs.rental.security.handler.CustomLoginSuccessHandler;
import org.bs.rental.security.handler.CustomOauth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
                    .successHandler(new CustomLoginSuccessHandler());
        });

        // csrf 토큰 비활성화
        http.csrf(config -> {
            config.disable();
        });

        // 자동 로그인
        http.rememberMe(config -> {

            config.tokenRepository(persistentTokenRepository());

            config.tokenValiditySeconds(60 * 60 * 24);
        });

        // oauth2 로그인
        http.oauth2Login(config -> {

            config.loginPage("/member/login");

            config.successHandler(new CustomOauth2SuccessHandler());
        });

        http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
