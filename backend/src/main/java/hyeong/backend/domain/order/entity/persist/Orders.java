package hyeong.backend.domain.order.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.order.entity.vo.OrderNumber;
import hyeong.backend.domain.order.entity.vo.OrderStatus;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "market_email" )
    private Market market;

    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "member_email" )
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<Item> itemList;

    @Embedded
    @Column(name = "order_number")
    private OrderNumber orderNumber;

    @Embedded
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Builder
    public Orders(
            Market market,
            Member member,
            List<Item> itemList,
            OrderNumber orderNumber,
            OrderStatus orderStatus
    ) {
        this.market = market;
        this.member = member;
        this.itemList = itemList;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }









}
