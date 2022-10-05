package hyeong.backend.domain.market.dto;

import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import lombok.Builder;
import lombok.Data;

@Data
public class MarketSearchRequestDTO {

    private MarketEmail marketEmail;
    private MarketName marketName;

    @Builder
    public MarketSearchRequestDTO(final MarketEmail marketEmail, final MarketName marketName) {
        this.marketEmail = marketEmail;
        this.marketName = marketName;
    }

}
