package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.LocationAddress;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeName("member")
public class MarketResponseDTO {

    @JsonProperty("marketName")
    private MarketName marketName;

    @JsonProperty("marketEmail")
    private MarketEmail marketEmail;

    @JsonProperty("locationAddress")
    private LocationAddress locationAddress;

    @Builder
    public MarketResponseDTO(
            MarketName marketName,
            MarketEmail marketEmail,
            LocationAddress locationAddress
    ) {
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.locationAddress = locationAddress;
    }
    public static MarketResponseDTO create(final Market market) {
        return new MarketResponseDTO(market.getMarketName(), market.getMarketEmail(), market.getLocationAddress());
    }
}
