package hyeong.backend.domain.market.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LocationCondition {
    private String city;
    private String town;

    @Builder
    public LocationCondition(final String city, final String town) {
        this.city = city;
        this.town = town;
    }
}
