package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.member.exceptions.PasswordMissMatchException;
import hyeong.backend.domain.member.exceptions.PasswordNullException;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MarketPassword {

    @NotNull(message = "비밀번호는 필수 입력 사항입니다.")
    @Column(name = "market_password", nullable = false , length = 100)
    private String password;

    public static MarketPassword encode(final String rawPassword, final PasswordEncoder passwordEncoder) {
        validateBlank(rawPassword);
        return new MarketPassword(passwordEncoder.encode(rawPassword));
    }

    private static void validateBlank(String rawPassword) {
        if (Objects.isNull(rawPassword) || rawPassword.isBlank()) {
            throw new PasswordNullException(ErrorCode.PASSWORD_NULL_ERROR);
        }
    }

    public void matches(final MarketPassword other, final PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(other.password(), this.password)) {
            throw new PasswordMissMatchException(ErrorCode.PASSWORD_MISS_MATCH);
        }
    }


    public static MarketPassword from(final String password) {
        return new MarketPassword(password);
    }

    @JsonValue
    public String password (){
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(password());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this != o || getClass() != o.getClass())return false;
        MarketPassword marketPassword = (MarketPassword) o;
        return Objects.equals(password(), marketPassword.password);
    }

}
