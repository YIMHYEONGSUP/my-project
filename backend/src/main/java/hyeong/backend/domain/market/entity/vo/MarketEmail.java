package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MarketEmail implements Serializable {

    @Email
    @NotNull(message = "이메일은 필수 입력사항 입니다.")
    private String marketEmail;

    public static MarketEmail from(final String email) {
        return new MarketEmail(email);
    }

    @JsonValue
    public String email() {
        return marketEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketEmail marketEmail = (MarketEmail) o;
        return Objects.equals(email(), marketEmail.marketEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email());
    }
}
