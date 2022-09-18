package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.GivenMarket;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.market.repository.ReviewRepository;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.util.GivenMember;
import hyeong.backend.global.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import(TestConfig.class)
@Slf4j
class MarketRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    ReviewRepository reviewRepository;

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

        log.info("Review = {} , {} , {} , {}" , newReview.getMarket().getName().name() , newReview.getMember().getName().name() , newReview.getRating() , newReview.getComments().comments());


    }




}