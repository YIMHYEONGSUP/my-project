package hyeong.backend.domain.market.dto.marketUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.controller.MarketControlResponseDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
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
        return new MarketResponseDTO(market.getMarketName(), market.getMarketEmail(), market.getMarketLocationAddress());
    }

    public static MarketControlResponseDTO from(Market save) {
        return MarketControlResponseDTO.builder()
                .marketStatus(MarketStatus.OPEN)
                .preparedItemList(save.getItemList())
                .build();
    }

    public Market toEntity() {
        return Market.builder()
                .marketName(name)
                .marketEmail(email)
                .marketLocationAddress(locationAddress)
                .build();
    }

}
