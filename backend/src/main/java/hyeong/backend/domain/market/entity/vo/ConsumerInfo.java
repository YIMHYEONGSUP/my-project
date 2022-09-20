package hyeong.backend.domain.market.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerInfo {

    private Long inside;

    private Long outSide;
}
