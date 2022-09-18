package hyeong.backend.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;

@Getter
@JsonTypeName("item")
@AllArgsConstructor
public class ItemRegisterResponseDTO {

    @JsonProperty("category")
    private ItemCategory itemCategory;

    @JsonProperty("code")
    private ItemCode itemCode;

    @JsonProperty("status")
    private ItemStatus itemStatus;

    @JsonProperty("name")
    private ItemName itemName;

    @JsonProperty("price")
    private ItemPrice itemPrice;

    @JsonProperty("quantity")
    private ItemQuantity itemQuantity;

    public static ItemRegisterResponseDTO from(final Item item) {
        return new ItemRegisterResponseDTO(
                item.getItemCategory(), item.getItemCode(),
                item.getItemStatus(), item.getItemName(),
                item.getItemPrice(), item.getItemQuantity());
    }


}
