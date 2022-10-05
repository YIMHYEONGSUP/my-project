package hyeong.backend.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.persist.Market;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(of = {"id","itemCategory,","itemCode","itemStatus","itemName","itemPrice","itemQuantity"})
public class ItemSearchRequestDTO {


    private Long id;
    private Market market;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @Enumerated(EnumType.STRING)
    private ItemCode itemCode;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Embedded
    private ItemName itemName;

    @Embedded
    private ItemPrice itemPrice;

    @Embedded
    private ItemQuantity itemQuantity;

    @QueryProjection
    public ItemSearchRequestDTO(final Long id, final Market market, final ItemCategory itemCategory, final ItemCode itemCode,
                                final ItemStatus itemStatus, final ItemName itemName, final ItemPrice itemPrice, final ItemQuantity itemQuantity) {
        this.id = id;
        this.market = market;
        this.itemCategory = itemCategory;
        this.itemCode = itemCode;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }


}
