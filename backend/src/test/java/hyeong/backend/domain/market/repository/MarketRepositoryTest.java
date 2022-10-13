package hyeong.backend.domain.market.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.auth.config.WithMockCustomerMarket;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.QItemResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.entity.persist.QItem;
import hyeong.backend.domain.item.entity.vo.*;
import hyeong.backend.domain.item.repository.ItemRepository;
import hyeong.backend.domain.market.dto.MarketListResponseDTO;
import hyeong.backend.domain.market.dto.QMarketListResponseDTO;
import hyeong.backend.domain.market.dto.item.MarketItemListResponseDTO;
import hyeong.backend.domain.market.entity.persist.QMarket;
import hyeong.backend.global.configs.SecurityConfig;
import hyeong.backend.global.given.GivenItem;
import hyeong.backend.global.given.GivenMarket;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.global.given.GivenMember;
import hyeong.backend.global.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static hyeong.backend.domain.item.entity.persist.QItem.*;
import static hyeong.backend.domain.market.entity.persist.QMarket.*;

@DataJpaTest(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
@RunWith(SpringRunner.class)
@Import(TestConfig.class)
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class MarketRepositoryTest {


    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory query;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;


    @BeforeEach
    @WithMockCustomerMarket
    public void before() {
        System.out.println("execute before ");

        for (int i = 0; i < 10; i++) {
            Market orderedMarket = GivenMarket.createOrderedMarket(i);
            Item orderedItem = GivenItem.createOrderedItem(i, orderedMarket);

            marketRepository.save(orderedMarket);
            itemRepository.save(orderedItem);

            for (int j = 0; j < 5; j++) {
                Item item = Item.builder()
                        .itemCategory(ItemCategory.FOOD)
                        .itemCode(ItemCode.KOREAN)
                        .itemStatus(ItemStatus.FOR_SALE)
                        .itemName(ItemName.from("고추장 불고기" + j+i))
                        .itemPrice(ItemPrice.from(13000L))
                        .itemQuantity(ItemQuantity.from(100L))
                        .build();
                item.setMarket(orderedMarket);
                itemRepository.save(item);
            }

        }

  

    }



    @Test
    @DisplayName("Repository Test")
    public void repoTest() {

        Member member = GivenMember.createMember();
        Member newMember = memberRepository.save(member);

        Market market = GivenMarket.createMarket();
        Market newMarket = marketRepository.save(market);

        Review review = Review.builder()
                .market(newMarket)
                .member(newMember)
                .rating(Rating.FIVE)
                .comments(Comments.from("5점 드립니다."))
                .build();

        Review newReview = reviewRepository.save(review);

        log.info("Review = {} , {} , {} , {}" , newReview.getMarket().getMarketName().marketName() , newReview.getMember().getMemberName().memberName() , newReview.getRating() , newReview.getComments().comments());


    }

    @Test
    @DisplayName("get itemList test")
    public void itemListTest() {


        for (int i = 0; i < 100; i++) {
            Market orderedMarket = GivenMarket.createOrderedMarket(i);
            marketRepository.save(orderedMarket);

            for (int j = 0; j < 10; j++) {
                Item orderedItem = GivenItem.createOrderedItem(i , orderedMarket);
                itemRepository.save(orderedItem);
            }
        }

        Page<MarketItemListResponseDTO> itemLists =
                marketRepository.marketItemList(MarketEmail.from("marketName7"), PageRequest.of(0, 10));

        itemLists.forEach((value)->{
            System.out.println("value = " + value.getId() + " " + value.getItemName().name());
        });

    }

    @Test
    public void queryDSL() {

        String city = "부천";
        String town = "중동";

        QueryResults<MarketListResponseDTO> response = query.select(new QMarketListResponseDTO(
                        market.marketName,
                        market.marketStatus,
                        market.marketLocationAddress
                ))
                .from(market)
                .where(marketCityEq(city),
                        marketTownEq(town))
                .fetchResults();

        long total = response.getTotal();
        List<MarketListResponseDTO> result = response.getResults();
        System.out.println("결과 사이즈 / 총 검색결과 " + result.size() + "/" + total);
        for (MarketListResponseDTO r : result) {
            System.out.println(r.getMarketName().marketName() + " " + r.getMarketStatus() + " " + r.getLocationAddress().getCity() + " " + r.getLocationAddress().getTown());
        }


    }

    @Test
    @DisplayName("변경감지 테스트")
    public void dirtyChecking() {

        String MARKET_EMAIL = "market0@gmail.com";

        Optional<Market> findMarket = marketRepository.findByMarketEmail(MarketEmail.from(MARKET_EMAIL));
        Market market = findMarket.get();
        market.changeStatus(MarketStatus.OPEN);

        em.flush();
        em.clear();

        Optional<Market> findMarket2 = marketRepository.findByMarketEmail(MarketEmail.from(MARKET_EMAIL));
        Market market2 = findMarket2.get();
        log.info("마켓 상태 = {}",market2.getMarketStatus());

        List<ItemResponseDTO> findItem = query.select(new QItemResponseDTO(
                        QItem.item.itemCategory,
                        QItem.item.itemCode,
                        QItem.item.itemStatus,
                        QItem.item.itemName,
                        QItem.item.itemPrice,
                        QItem.item.itemQuantity
                )).from(QItem.item)
                .where(MarketEmailEq(MARKET_EMAIL))
                .fetch();

        findItem.forEach(itemResponseDTO -> {
            itemResponseDTO.setItemStatus(ItemStatus.FOR_SALE);
        });


    }

    private BooleanExpression MarketEmailEq(String marketEmail) {
        return StringUtils.hasText(marketEmail) ? market.marketEmail.marketEmail.eq(marketEmail) : null;
    }


    // methods

    private BooleanExpression marketTownEq(String town) {
        return StringUtils.hasText(town) ? market.marketLocationAddress.town.eq(town) : null;
    }

    private BooleanExpression marketCityEq(String city) {
        return StringUtils.hasText(city) ? market.marketLocationAddress.city.eq(city) : null;
    }


}