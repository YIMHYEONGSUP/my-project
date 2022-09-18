package hyeong.backend.domain.item.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemPrice {

    @NotNull(message = "가격은 필수입니다.")
    private Long price;

    @JsonValue
    public Long price() {
        return price;
    }

    public static ItemPrice from(final Long price) {
        return new ItemPrice(price);
    }
}
