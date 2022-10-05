package hyeong.backend.domain.market.dto.marketUser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.global.common.vo.LocationAddress;
import hyeong.backend.global.common.vo.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
@Slf4j
public class MarketJoinRequestDTOSerialize {

    @JsonProperty("market_name")
    private MarketName name;

    @JsonProperty("market_email")
    private MarketEmail email;

    @JsonProperty("market_password")
    private MarketPassword password;

    @JsonProperty("market_location_address")
    private LocationAddress locationAddress;

    @JsonCreator
    public MarketJoinRequestDTOSerialize (
            @JsonProperty("market_name") final MarketName name,  @JsonProperty("market_email") final MarketEmail email,
            @JsonProperty("market_password") final MarketPassword password,
            @JsonProperty("market_location_address") LocationAddress locationAddress
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
    }

    public Market toEntity() {
        return Market.builder()
                .marketName(name)
                .marketEmail(email)
                .marketPassword(password)
                .marketLocationAddress(locationAddress)
                .marketRoleType(RoleType.MARKET)
                .marketStatus(MarketStatus.PREPARED)
                .build();
    }

}
