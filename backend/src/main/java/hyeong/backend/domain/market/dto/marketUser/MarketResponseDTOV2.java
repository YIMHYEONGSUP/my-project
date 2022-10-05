package hyeong.backend.domain.market.dto.marketUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
public class MarketResponseDTOV2 {

    @JsonProperty("market_name")
    private MarketName name;

    @JsonProperty("market_email")
    private MarketEmail email;

    @JsonProperty("market_password")
    private MarketPassword password;

    @JsonProperty("market_location_address")
    private LocationAddress locationAddress;

    @Builder
    public MarketResponseDTOV2(
            MarketName name,
            MarketEmail email,
            MarketPassword password,
            LocationAddress locationAddress
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
    }
    public static MarketResponseDTOV2 create(final Market market) {
        return new MarketResponseDTOV2(market.getMarketName(), market.getMarketEmail(),market.getMarketPassword(), market.getMarketLocationAddress());
    }

    public Market toEntity() {
        return Market.builder()
                .marketName(name)
                .marketEmail(email)
                .marketPassword(password)
                .marketLocationAddress(locationAddress)
                .build();
    }

}
