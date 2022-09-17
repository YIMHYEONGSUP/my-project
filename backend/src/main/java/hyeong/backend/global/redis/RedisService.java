package hyeong.backend.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private String REFRESH_TOKEN = "refreshToken";
    private String ACCESS_TOKEN = "accessToken";

    private final RedisTemplate redisTemplate;

    public void setRefreshToken(String refreshToken, long seconds) {
        ValueOperations<String , String> values = redisTemplate.opsForValue();
        values.set(refreshToken , REFRESH_TOKEN , Duration.ofSeconds(seconds));
    }

    public String getRefreshToken(String refreshToken) {
        return ((ValueOperations<String, String>) redisTemplate.opsForValue()).get(refreshToken);
    }

    public void setBlackList(String accessToken, long seconds) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(accessToken , ACCESS_TOKEN , Duration.ofSeconds(seconds));
    }

    public String getBlacklist(String accessToken) {
        return ((ValueOperations<String, String>) redisTemplate.opsForValue()).get(accessToken);
    }
}
