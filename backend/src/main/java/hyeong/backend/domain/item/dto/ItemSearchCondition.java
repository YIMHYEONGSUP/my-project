package hyeong.backend.domain.item.dto;

import hyeong.backend.domain.item.entity.vo.*;
import lombok.Data;

@Data
public class ItemSearchCondition {

    private ItemCategory itemCategory;
    private ItemCode itemCode;
    private ItemStatus itemStatus;
    private ItemName itemName;
    private ItemPrice itemPrice;
    private ItemQuantity itemQuantity;

}
