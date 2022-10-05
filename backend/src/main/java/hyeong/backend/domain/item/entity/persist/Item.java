package hyeong.backend.domain.item.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH , fetch = FetchType.LAZY)
    @JoinColumn(name = "market_email")
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


    @Builder
    public Item(
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

    public void setMarket(final Market market) {
        this.market = market;
    }


}
