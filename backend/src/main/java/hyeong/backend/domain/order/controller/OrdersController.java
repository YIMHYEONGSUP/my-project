package hyeong.backend.domain.order.controller;

import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.service.MarketService;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.service.MemberService;
import hyeong.backend.domain.order.dto.OrdersRequestDTO;
import hyeong.backend.domain.order.dto.OrdersResponseDTO;
import hyeong.backend.domain.order.entity.persist.Orders;
import hyeong.backend.domain.order.entity.vo.OrderStatus;
import hyeong.backend.domain.order.service.OrdersService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("주문 api")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;
    private final MarketService marketService;
    private final MemberService memberService;
    private final ItemService itemService;


    @PostMapping
    public ResponseEntity<OrdersResponseDTO> order (
            @RequestBody OrdersRequestDTO requestDTO
            ) {

        log.info("오더 실행이 아예 안됨");

        MarketEmail marketEmail = requestDTO.getMarketEmail();
        MemberEmail memberEmail = requestDTO.getMemberEmail();
        List<Item> itemList = requestDTO.getItemList();

        Market market = marketEmail.toEntity();
        Member member = memberEmail.toEntity();

        log.info("주문서 발급 전");
        // 주문서 발급
        Orders orders = Orders.builder()
                .market(market)
                .member(member)
                .itemList(itemList)
                .orderStatus(OrderStatus.PREPARE)
                .build();
        log.info("주문서 발급 전2");

        OrdersResponseDTO orders1 = ordersService.orders(orders);
        log.info("주문서 발급 전3");

        log.info("number = {} , market = {} , member = {}" , orders1.getOrderNumber().orderNumber() , orders1.getMarket().getMarketEmail().email() , orders1.getMember().getMemberEmail().memberEmail());
        return ResponseEntity.ok(orders1);
    }


}
