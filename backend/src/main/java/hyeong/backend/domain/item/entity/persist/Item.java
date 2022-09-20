package hyeong.backend.domain.item.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.persist.Market;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @Enumerated
    private ItemCategory itemCategory;

    @Enumerated
    private ItemCode itemCode;

    @Enumerated
    private ItemStatus itemStatus;


    @Embedded
    private ItemName itemName;

    @Embedded
    private ItemPrice itemPrice;

    @Embedded
    private ItemQuantity itemQuantity;


    @Builder
    public Item(
            Market market,
            ItemCategory itemCategory,
            ItemCode itemCode,
            ItemStatus itemStatus,
            ItemName itemName,
            ItemPrice itemPrice,
            ItemQuantity itemQuantity
    ) {
        this.market = market;
        this.itemCategory = itemCategory;
        this.itemCode = itemCode;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }




}
