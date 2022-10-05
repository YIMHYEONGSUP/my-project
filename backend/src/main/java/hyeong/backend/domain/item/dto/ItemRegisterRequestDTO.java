package hyeong.backend.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Getter
@Slf4j
@ApiModel
@JsonTypeName("item")
@NoArgsConstructor
public class ItemRegisterRequestDTO {

    @Valid
    @JsonProperty("item_category")
    private ItemCategory itemCategory;

    @Valid
    @JsonProperty("item_code")
    private ItemCode itemCode;

    @Valid
    @JsonProperty("item_status")
    private ItemStatus itemStatus;

    @Valid
    @JsonProperty("item_name")
    private ItemName itemName;

    @Valid
    @JsonProperty("item_price")
    private ItemPrice itemPrice;

    @Valid
    @JsonProperty("item_quantity")
    private ItemQuantity itemQuantity;

    @Builder
    public ItemRegisterRequestDTO(
            ItemCategory itemCategory,
            ItemCode itemCode,
            ItemStatus itemStatus,
            ItemName itemName,
            ItemPrice itemPrice,
            ItemQuantity itemQuantity
    ) {
        this.itemCategory = itemCategory;
        this.itemCode = itemCode;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public Item toEntity() {
        return Item.builder()
                .itemCategory(itemCategory)
                .itemCode(itemCode)
                .itemStatus(itemStatus)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemQuantity(itemQuantity)
                .build();
    }


}
