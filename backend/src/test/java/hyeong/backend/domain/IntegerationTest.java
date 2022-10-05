package hyeong.backend.domain;

import hyeong.backend.domain.event.dto.EventRegisterResponseDTO;
import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.event.entity.vo.EventContents;
import hyeong.backend.domain.event.entity.vo.EventSubject;
import hyeong.backend.domain.event.service.EventService;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.global.given.GivenMarket;
import hyeong.backend.domain.market.dto.marketUser.MarketJoinResponseDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.service.MarketService;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.global.given.GivenMember;
import hyeong.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
@RequiredArgsConstructor
public class IntegerationTest {

    @Autowired
    EntityManager em;

    @Autowired
    private MarketService marketService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EventService eventService;

    @Autowired
    private MemberService memberService;

    static Member member = GivenMember.createMember();
    static Market market = GivenMarket.createMarket();

    @Test
    @DisplayName("통합 테스트")
    public void test() {

        MemberJoinResponseDTO memberJoinResponseDTO = memberService.create(member);
        System.out.println("memberJoinResponseDTO = " + memberJoinResponseDTO);

        MarketJoinResponseDTO marketJoinResponseDTO = marketService.create(market);
        System.out.println("marketJoinResponseDTO = " + marketJoinResponseDTO);

        Item item = Item.builder()
                .itemCategory(ItemCategory.FOOD)
                .itemCode(ItemCode.KOREAN)
                .itemStatus(ItemStatus.FOR_SALE)
                .itemName(ItemName.from("고추장 불고기"))
                .itemPrice(ItemPrice.from(13000L))
                .itemQuantity(ItemQuantity.from(100L))
                .build();

        item.setMarket(market);

        ItemRegisterResponseDTO itemRegisterResponseDTO = itemService.create(item);
        System.out.println("itemRegisterResponseDTO = " + itemRegisterResponseDTO);

        Event event = Event.builder()
                .market(market)
                .subject(EventSubject.from("오픈 행사"))
                .contents(EventContents.from("오픈 행사 반값 서비스"))
                .build();

        EventRegisterResponseDTO eventResponseDTO = eventService.createEvent(event);
        System.out.println("eventResponseDTO = " + eventResponseDTO);
        System.out.println("eventResponseDTO.getMarket() = " + eventResponseDTO.getMarket().getMarketName().marketName());

    }
}
