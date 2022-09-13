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
public class InsideCustomerInfo {

    private long inSideCustomerCount;

    public static InsideCustomerInfo init() {
        return new InsideCustomerInfo(0L);
    }

    public static InsideCustomerInfo from(final long inSideCustomerCount) {
        return new InsideCustomerInfo(inSideCustomerCount);
    }

    @JsonValue
    public long inSideCustomerCount() {
        return inSideCustomerCount;
    }
}
