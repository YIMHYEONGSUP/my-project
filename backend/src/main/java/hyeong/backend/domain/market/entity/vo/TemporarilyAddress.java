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
public class TemporarilyAddress {

    private String address;

    public static TemporarilyAddress from(final String address) {
        return new TemporarilyAddress(address);
    }

    @JsonValue
    public String address() {
        return address;
    }


}
