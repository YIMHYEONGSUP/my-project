package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
public class MarketResponseDTO {

    @JsonProperty("market_name")
    private MarketName name;

    @JsonProperty("market_email")
    private MarketEmail email;

    @JsonProperty("market_location_address")
    private LocationAddress locationAddress;

    @Builder
    public MarketResponseDTO(
            MarketName name,
            MarketEmail email,
            LocationAddress locationAddress
    ) {
        this.name = name;
        this.email = email;
        this.locationAddress = locationAddress;
    }
    public static MarketResponseDTO create(final Market market) {
        return new MarketResponseDTO(market.getName(), market.getEmail(), market.getLocationAddress());
    }

    public Market toEntity() {
        return Market.builder()
                .name(name)
                .email(email)
                .locationAddress(locationAddress)
                .build();
    }

}
