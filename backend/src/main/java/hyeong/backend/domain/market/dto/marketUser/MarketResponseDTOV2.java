package hyeong.backend.domain.market.dto.marketUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("market")
public class MarketResponseDTOV2 {

    private MarketStatus marketStatus;

    @JsonProperty("market_name")
    private MarketName marketName;

    @JsonProperty("market_email")
    private MarketEmail marketEmail;

    @JsonProperty("market_password")
    private MarketPassword marketPassword;

    @JsonProperty("market_location_address")
    private LocationAddress locationAddress;

    @Builder
    public MarketResponseDTOV2(
            MarketStatus marketStatus,
            MarketName marketName,
            MarketEmail marketEmail,
            MarketPassword marketPassword,
            LocationAddress locationAddress
    ) {
        this.marketStatus = marketStatus;
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.marketPassword = marketPassword;
        this.locationAddress = locationAddress;
    }
    public static MarketResponseDTOV2 create(final Market market) {
        return new MarketResponseDTOV2(market.getMarketStatus(), market.getMarketName(), market.getMarketEmail(),market.getMarketPassword(), market.getMarketLocationAddress());
    }

    public Market toEntity() {
        return Market.builder()
                .marketStatus(marketStatus)
                .marketName(marketName)
                .marketEmail(marketEmail)
                .marketPassword(marketPassword)
                .marketLocationAddress(locationAddress)
                .build();
    }

}
