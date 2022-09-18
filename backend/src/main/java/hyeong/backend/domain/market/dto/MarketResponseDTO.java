package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.LocationAddress;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.TemporarilyAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeName("member")
public class MarketResponseDTO {

    @JsonProperty("marketName")
    private MarketName name;

    @JsonProperty("marketEmail")
    private MarketEmail email;

    @JsonProperty("locationAddress")
    private TemporarilyAddress locationAddress;

    @Builder
    public MarketResponseDTO(
            MarketName name,
            MarketEmail email,
            TemporarilyAddress locationAddress
    ) {
        this.name = name;
        this.email = email;
        this.locationAddress = locationAddress;
    }
    public static MarketResponseDTO create(final Market market) {
        return new MarketResponseDTO(market.getName(), market.getEmail(), market.getLocationAddress());
    }
}
