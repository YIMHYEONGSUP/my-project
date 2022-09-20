package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonTypeName("market")
@AllArgsConstructor
public class MarketJoinResponseDTO {

    @JsonProperty("marketName")
    private MarketName name;

    @JsonProperty("marketEmail")
    private MarketEmail email;

    @JsonProperty("locationAddress")
    private LocationAddress locationAddress;

    public static MarketJoinResponseDTO from(final Market market) {
        return new MarketJoinResponseDTO(market.getName() , market.getEmail() , market.getLocationAddress());
    }
}
