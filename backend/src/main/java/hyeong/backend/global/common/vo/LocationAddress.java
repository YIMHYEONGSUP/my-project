package hyeong.backend.global.common.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

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
