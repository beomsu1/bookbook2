package org.bs.rental.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        
        // host, port를 redis에 연결
        return new LettuceConnectionFactory(redisHost,redisPort);
    }

    // RedisTemplate -> Redis에 데이터를 저장하고 검색하는데 사용되는 클래스
    @Bean
    public RedisTemplate<?,?> redisTemplate(){
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();

        // redisConnection을 통해 받은 byte값을 객체 직렬화
        redisTemplate.setConnectionFactory((redisConnectionFactory()));
        return redisTemplate;
    }
    
}
