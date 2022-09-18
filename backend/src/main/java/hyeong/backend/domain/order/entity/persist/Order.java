package hyeong.backend.domain.order.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.order.entity.vo.OrderNumber;
import hyeong.backend.domain.order.entity.vo.OrderStatus;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

//@Entity 미완
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "market_name")
    @Column(name = "order_market_name")
    private Market market;

    @OneToMany(mappedBy = "member_nickname")
    @Column(name = "order_member_id")
    private Member member;

    @Embedded
    @Column(name = "order_number")
    private OrderNumber orderNumber;

    @Embedded
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Builder
    public Order (
            Market market,
            Member member,
            OrderNumber orderNumber,
            OrderStatus orderStatus
    ) {
        this.market = market;
        this.member = member;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }









}
