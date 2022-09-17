package hyeong.backend.global.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.PatternMatchUtils;

import java.time.Duration;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataRedisTest
class RedisServiceTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        ValueOperations<String , String> values = redisTemplate.opsForValue();
        long expTime = 1000 * 10;

        values.set("key1", "value1" , Duration.ofSeconds(expTime));

        String content = values.get("key1");
        System.out.println("content = " + content);

        String key2 = values.get("key2");
        System.out.println("key2 = " + key2);



    }

}