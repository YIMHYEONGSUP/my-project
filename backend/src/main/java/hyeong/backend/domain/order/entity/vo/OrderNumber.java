package hyeong.backend.domain.order.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderNumber {

    @GeneratedValue
    private Long orderNumber;

    @JsonValue
    public Long orderNumber() {
        return orderNumber;
    }
}
