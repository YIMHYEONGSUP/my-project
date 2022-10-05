package hyeong.backend.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.querydsl.core.annotations.QueryProjection;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
public class ItemResponseDTO {

    @JsonProperty("item_category")
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @JsonProperty("item_code")
    @Enumerated(EnumType.STRING)
    private ItemCode itemCode;

    @JsonProperty("item_status")
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @JsonProperty("item_name")
    private ItemName itemName;

    @JsonProperty("item_price")
    private ItemPrice itemPrice;

    @JsonProperty("item_quantity")
    private ItemQuantity itemQuantity;

    public static ItemResponseDTO from(final Item item) {
        return new ItemResponseDTO(
                item.getItemCategory(), item.getItemCode(),
                item.getItemStatus(), item.getItemName(),
                item.getItemPrice(), item.getItemQuantity());
    }

    @QueryProjection
    public ItemResponseDTO (final ItemCategory itemCategory , final ItemCode itemCode,
                            final ItemStatus itemStatus , final ItemName itemName,
                            final ItemPrice itemPrice , final ItemQuantity itemQuantity
    ) {
        this.itemCategory = itemCategory;
        this.itemCode = itemCode;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }


}
