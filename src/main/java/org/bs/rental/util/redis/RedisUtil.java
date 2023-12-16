package org.bs.rental.util.redis;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisUtil {

    // StringRedisTemplate -> Redis에 문자열 데이터 저장, 검색. RedisTemplate의 확장
    private final StringRedisTemplate template;

    // 데이터 검색
    public String getDate(String key){

        // ValueOperations -> Redis의 String 데이터 유형에 대한 기본적인 작업을 수행하는 데 사용
        ValueOperations<String, String> valueOperations = template.opsForValue(); // opsForValue -> Redis에 문자열 값을 저장, 검색
        return valueOperations.get(key);
    }

    // 데이터 존재 여부 확인
    public boolean existData(String key){
        return Boolean.TRUE.equals(template.hasKey(key));
    }

    // 데이터 저장, 만료 시간 설정
    public void setDataExpire(String key, String value, long duration){
        ValueOperations<String, String> valueOperations = template.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    // 데이터 삭제
    public void deleteData(String key){
        template.delete(key);
    }

    public boolean existData(Object key) {
        return false;
    }


    
}
