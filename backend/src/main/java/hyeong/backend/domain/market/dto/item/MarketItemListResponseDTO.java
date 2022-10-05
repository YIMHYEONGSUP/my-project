package hyeong.backend.domain.market.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import hyeong.backend.domain.item.entity.vo.ItemName;
import hyeong.backend.domain.item.entity.vo.ItemPrice;
import hyeong.backend.domain.item.entity.vo.ItemQuantity;
import lombok.Data;

@Data
public class MarketItemListResponseDTO {

    private Long id;

    private ItemName itemName;
    private ItemPrice itemPrice;
    private ItemQuantity itemQuantity;

    @QueryProjection
    public MarketItemListResponseDTO(final Long id, final ItemName itemName, final ItemPrice itemPrice, final ItemQuantity itemQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
}
