package hyeong.backend.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.order.entity.persist.Orders;
import hyeong.backend.domain.order.entity.vo.OrderNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.annotation.Order;

@Data
@AllArgsConstructor
@JsonTypeName("orders")
public class OrdersResponseDTO {

    private Market market;

    private Member member;

    private OrderNumber orderNumber;

    public static OrdersResponseDTO from(Orders orders) {
        return new OrdersResponseDTO(orders.getMarket(), orders.getMember(), orders.getOrderNumber());
    }
}
