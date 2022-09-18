package hyeong.backend.domain.market.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeName("market")
public class MarketJoinRequestDTO {

    private MarketName name;

    private MarketEmail email;

    private MarketPassword password;

    private TemporarilyAddress locationAddress;

    @Builder
    public MarketJoinRequestDTO(final MarketName name, final MarketEmail email, final MarketPassword password , final TemporarilyAddress locationAddress) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
    }

    public static MarketJoinRequestDTO from(final Market market) {
        return new MarketJoinRequestDTO(market.getName(), market.getEmail(), market.getPassword() , market.getLocationAddress());
    }

    public Market toEntity() {
        return Market.builder()
                .name(name)
                .email(email)
                .password(password)
                .locationAddress(locationAddress)
                .build();
    }

}
