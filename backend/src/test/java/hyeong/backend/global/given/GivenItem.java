package hyeong.backend.global.given;

import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.persist.Market;

public class GivenItem {

    public static Item createItem() {

        return Item.builder()
                .itemCategory(ItemCategory.FOOD)
                .itemCode(ItemCode.KOREAN)
                .itemStatus(ItemStatus.FOR_SALE)
                .itemName(ItemName.from("고추장 불고기"))
                .itemPrice(ItemPrice.from(13000L))
                .itemQuantity(ItemQuantity.from(100L))
                .build();
    }

    public static Item createOrderedItem(int index , Market market) {

        Item item = Item.builder()
                .itemCategory(ItemCategory.FOOD)
                .itemCode(ItemCode.KOREAN)
                .itemStatus(ItemStatus.FOR_SALE)
                .itemName(ItemName.from("고추장 불고기" + index))
                .itemPrice(ItemPrice.from(13000L))
                .itemQuantity(ItemQuantity.from(100L))
                .build();

        item.setMarket(market);

        return item;
    }

}
