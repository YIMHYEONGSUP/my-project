package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.global.common.vo.LocationAddress;
import hyeong.backend.global.common.vo.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@Slf4j
class MarketTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void market() {


        Member newMember = Member.builder()
                .memberEmail(MemberEmail.from("gud1313@naver.com"))
                .memberPassword(MemberPassword.from("1234"))
                .memberName(MemberName.from("임형섭"))
                .memberNickName(MemberNickName.from("별명"))
                .memberRoleType(RoleType.USER)
                .reviews(null)
                .build();

        Review newReview = Review.builder()
                .member(newMember)
                .rating(Rating.FIVE)
                .comments(Comments.from("별점 5점 줄만함"))
                .build();

        ;
        List<Review> list = new ArrayList<>();
        list.add(newReview);


        Market market = Market.builder()
                .marketName(MarketName.from("가게이름"))
                .marketEmail(MarketEmail.from("가게이메일@aa.com"))
                .marketLocationAddress(LocationAddress.from("부천","중동","집주소","12345"))
                .reviews(list)
                .build();

        log.info(market.getMarketName().marketName());

        List<Review> reviews = market.getReviews();
        for (Review review : reviews) {
            log.info("리뷰 = " + review.getComments().comments());
        }

    }

}