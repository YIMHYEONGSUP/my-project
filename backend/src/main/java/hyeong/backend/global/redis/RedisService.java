package hyeong.backend.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void setBlackList(String token, String tokenName, long seconds) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token , tokenName , Duration.ofSeconds(seconds));
    }

    public String getBlacklist(String token) {
        return ((ValueOperations<String, String>) redisTemplate.opsForValue()).get(token);
    }
}
