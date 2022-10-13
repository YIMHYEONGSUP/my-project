package hyeong.backend.domain.market.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MarketControlResponseDTO {

    @JsonProperty("market_status")
    private MarketStatus marketStatus;

    @JsonProperty("prepared_item_list")
    private List<Item> preparedItemList;

    @Builder
    public MarketControlResponseDTO (
            final MarketStatus marketStatus,
            final List<Item> preparedItemList
    ) {
        this.marketStatus = marketStatus;
        this.preparedItemList = preparedItemList;
    }

    public static MarketControlResponseDTO from(Market market) {
        return MarketControlResponseDTO.builder()
                .marketStatus(market.getMarketStatus())
                .preparedItemList(market.getItemList())
                .build();
    }

}
