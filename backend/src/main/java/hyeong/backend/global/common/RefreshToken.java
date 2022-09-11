package hyeong.backend.global.common;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Ref;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {

    @NotBlank(message = "토큰이 존재하지 않습니다.")
    private String refreshToken;

    @JsonValue
    public String getRefreshToken() {
        return refreshToken;
    }

    public static RefreshToken from(String refreshToken) {
        return new RefreshToken(refreshToken);
    }

}
