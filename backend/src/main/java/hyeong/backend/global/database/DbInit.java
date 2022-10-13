package hyeong.backend.global.database;

import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.item.repository.ItemRepository;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.order.repository.OrdersRepository;
import hyeong.backend.global.common.vo.LocationAddress;
import hyeong.backend.global.common.vo.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DbInit {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void create() {

        for (int i = 0; i < 10; i++) {

            // encoding passwords
            MemberPassword memberPassword = MemberPassword.encode("1234", passwordEncoder);
            MarketPassword marketPassword = MarketPassword.encode("1234", passwordEncoder);

            Member member = Member.builder()
                    .memberEmail(MemberEmail.from("member" + i + "@gmail.com"))
                    .memberPassword(memberPassword)
                    .memberName(MemberName.from("member" + i))
                    .memberRoleType(RoleType.USER)
                    .memberNickName(MemberNickName.from("me" + i))
                    .build();

            Market market = Market.builder()
                    .marketEmail(MarketEmail.from("market" + i + "@gmail.com"))
                    .marketPassword(marketPassword)
                    .marketRoleType(RoleType.MARKET)
                    .marketStatus(MarketStatus.PREPARED)
                    .marketName(MarketName.from("marketName" + i))
                    .marketLocationAddress(LocationAddress.from("부천", "중동", "집주소", "12345"))
                    .reviews(null)
                    .events(null)
                    .build();

            marketRepository.save(market);
            memberRepository.save(member);

            for (int j = 0; j < 5; j++) {
                Item item = Item.builder()
                        .itemCategory(ItemCategory.FOOD)
                        .itemCode(ItemCode.KOREAN)
                        .itemStatus(ItemStatus.FOR_SALE)
                        .itemName(ItemName.from("고추장 불고기" + j+i))
                        .itemPrice(ItemPrice.from(13000L))
                        .itemQuantity(ItemQuantity.from(100L))
                        .build();
                item.setMarket(market);
                itemRepository.save(item);
            }
        }

    }



}
