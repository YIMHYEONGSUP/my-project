package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OutSideCustomerInfo {

    private long outSideCustomerCount;

    public static OutSideCustomerInfo init() {
        return new OutSideCustomerInfo(0L);
    }

    public static OutSideCustomerInfo from(final long outSideCustomerCount) {
        return new OutSideCustomerInfo(outSideCustomerCount);
    }



    @JsonValue
    public long outSideCustomerCount() {
        return outSideCustomerCount;
    }

}
