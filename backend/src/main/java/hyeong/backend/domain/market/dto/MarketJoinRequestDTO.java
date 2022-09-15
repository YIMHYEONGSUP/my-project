package hyeong.backend.domain.market.dto;

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
@JsonTypeName("market")
public class MarketJoinRequestDTO {

    private MarketName marketName;

    private MarketEmail marketEmail;

    private LocationAddress locationAddress;

    @Builder
    public MarketJoinRequestDTO(final MarketName marketName, final MarketEmail marketEmail, final LocationAddress locationAddress) {
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.locationAddress = locationAddress;
    }

    public static MarketJoinRequestDTO from(final Market market) {
        return new MarketJoinRequestDTO(market.getMarketName(), market.getMarketEmail(), market.getLocationAddress());
    }

    public Market toEntity() {
        return Market.builder()
                .marketName(marketName)
                .marketEmail(marketEmail)
                .locationAddress(locationAddress)
                .build();
    }

}
