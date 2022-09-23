package hyeong.backend.global.common.vo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;


@Getter
@Embeddable
@NoArgsConstructor
@JsonTypeName("location_address")
@EqualsAndHashCode
@Slf4j
public class LocationAddress {

    @JsonProperty("city")
    private String city;

    @JsonProperty("town")
    private String town;

    @JsonProperty("detailed_address")
    private String detailedAddress;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonCreator
    public LocationAddress (
            @JsonProperty("city") final String city ,     @JsonProperty("town") final String town,
            @JsonProperty("detailed_address") final String detailedAddress, @JsonProperty("postal_code") final String postalCode
            ) {
        this.city = city;
        this.town = town;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
    }

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
