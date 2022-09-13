package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.entity.vo.MemberName;
import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocationAddress {

    private String city;

    private String town;

    private String detailedAddress;

    private String postalCode;

    public static LocationAddress from(final String city , final String town , final String detailedAddress, final String postalCode) {
        return new LocationAddress(city, town, detailedAddress, postalCode);
    }

    @JsonValue
    public String city() {
        return city;
    }

    @JsonValue
    public String town() {
        return town;
    }

    @JsonValue
    public String detailedAddress() {
        return detailedAddress;
    }

    @JsonValue
    public String postalCode() {
        return postalCode;
    }

}
