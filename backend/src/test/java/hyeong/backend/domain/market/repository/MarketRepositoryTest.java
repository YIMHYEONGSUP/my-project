package hyeong.backend.domain.market.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.auth.config.WithMockCustomerMarket;
import hyeong.backend.domain.item.dto.ItemSearchCondition;
import hyeong.backend.domain.item.entity.persist.Item;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

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

        log.info("Review = {} , {} , {} , {}" , newReview.getMarket().getMarketName().marketName() , newReview.getMember().getName().name() , newReview.getRating() , newReview.getComments().comments());


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

    private BooleanExpression marketTownEq(String town) {
        return StringUtils.hasText(town) ? market.marketLocationAddress.town.eq(town) : null;
    }

    private BooleanExpression marketCityEq(String city) {
        return StringUtils.hasText(city) ? market.marketLocationAddress.city.eq(city) : null;
    }


}