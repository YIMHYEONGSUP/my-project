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
public class ItemQuantity {

    @NotNull(message = "재고는 필수 입니다.")
    private Long quantity;

    @JsonValue
    public Long quantity() {
        return quantity;
    }

    public static ItemQuantity from(final Long quantity) {
        return new ItemQuantity(quantity);
    }


}
