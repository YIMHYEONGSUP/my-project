package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.entity.vo.MemberName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MarketName {

    @NotNull(message = "이름은 필수 입력사항 입니다.")
    private String name;

    public static MarketName from(final String marketName) {
        return new MarketName(marketName);
    }

    @JsonValue
    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)return true;
        if(o == null || getClass() != o.getClass()) return false;

        MarketName marketName = (MarketName) o;
        return Objects.equals(name(), marketName.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name());
    }

}
