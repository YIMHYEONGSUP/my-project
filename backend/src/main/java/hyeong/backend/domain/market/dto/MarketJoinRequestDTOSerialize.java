package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
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

    @JsonProperty("name")
    private MarketName name;

    @JsonProperty("email")
    private MarketEmail email;

    @JsonProperty("password")
    private MarketPassword password;

    @JsonProperty("location_address")
    private LocationAddress locationAddress;

    @JsonCreator
    public MarketJoinRequestDTOSerialize (
            @JsonProperty("name") final MarketName name,  @JsonProperty("email") final MarketEmail email,
            @JsonProperty("password") final MarketPassword password,
            @JsonProperty("location_address") LocationAddress locationAddress
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
    }

    public Market toEntity() {
        return Market.builder()
                .name(name)
                .email(email)
                .password(password)
                .locationAddress(locationAddress)
                .roleType(RoleType.MARKET)
                .build();
    }

}
