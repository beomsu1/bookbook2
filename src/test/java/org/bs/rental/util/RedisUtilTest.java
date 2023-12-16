package org.bs.rental.util;

import org.bs.rental.util.redis.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class RedisUtilTest {
    
    @Autowired
    private RedisUtil redisUtil;

    @Test
    @DisplayName("Redis Util Test")
    public void redisUtilTest(){

        // Given
        log.info("Redis Util Test Start");

        String email = "user1@naver.com";
        String code = "qwe123";

        // When
        redisUtil.setDataExpire(email, code, 60*60);

        // Then
        Assertions.assertTrue(redisUtil.existData("user1@naver.com"));
        Assertions.assertEquals(redisUtil.getDate(email), "qwe123");

        log.info("Redis Util Test Complete");
    }
}
