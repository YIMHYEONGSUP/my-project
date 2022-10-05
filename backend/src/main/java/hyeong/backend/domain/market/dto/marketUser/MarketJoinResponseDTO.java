package hyeong.backend.domain.market.dto.marketUser;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketName;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MarketJoinResponseDTO {

    private MarketName name;

    @Builder
    public MarketJoinResponseDTO(final Market market) {
        this.name = market.getMarketName();
    }

    public static MarketJoinResponseDTO from(final Market market) {
        return new MarketJoinResponseDTO(market.getMarketName());
    }
}
