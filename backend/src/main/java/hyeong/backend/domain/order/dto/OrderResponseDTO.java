package hyeong.backend.domain.order.dto;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.order.entity.vo.OrderNumber;

public class OrderResponseDTO {

    private Market market;

    private Member member;

    private OrderNumber orderNumber;

}
