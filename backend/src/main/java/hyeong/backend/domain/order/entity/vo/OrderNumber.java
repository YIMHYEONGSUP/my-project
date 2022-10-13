package hyeong.backend.domain.order.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderNumber {

    @CreatedDate
    @Column(name = "release_time")
    private LocalDate releaseTime;
    @GeneratedValue
    private Long orderNumber;

    @JsonValue
    public Long orderNumber() {
        return orderNumber;
    }
}
