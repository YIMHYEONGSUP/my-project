package hyeong.backend.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;

@Getter
@JsonTypeName("item")
@AllArgsConstructor
public class ItemRegisterResponseDTO {

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

    public static ItemRegisterResponseDTO from(final Item item) {
        return new ItemRegisterResponseDTO(
                item.getItemCategory(), item.getItemCode(),
                item.getItemStatus(), item.getItemName(),
                item.getItemPrice(), item.getItemQuantity());
    }


}
