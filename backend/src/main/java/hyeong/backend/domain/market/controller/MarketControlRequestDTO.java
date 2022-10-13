package hyeong.backend.domain.market.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
@JsonTypeName("market_control_request")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MarketControlRequestDTO {

    @JsonProperty("market_status")
    private MarketStatus marketStatus;

    @JsonProperty("prepared_item_list")
    private List<Item> preparedItemList;

    public Market toEntity() {
        return Market.builder()
                .marketStatus(marketStatus)
                .build();
    }
}
