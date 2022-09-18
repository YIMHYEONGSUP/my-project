package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
                .email(MemberEmail.from("gud1313@naver.com"))
                .password(MemberPassword.from("1234"))
                .name(MemberName.from("임형섭"))
                .nickname(MemberNickName.from("별명"))
                .roleType(RoleType.USER)
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
                .name(MarketName.from("가게이름"))
                .email(MarketEmail.from("가게이메일@aa.com"))
                .locationAddress(TemporarilyAddress.from("임시주소"))
                .reviews(list)
                .build();

        log.info(market.getName().name());

        List<Review> reviews = market.getReviews();
        for (Review review : reviews) {
            log.info("리뷰 = " + review.getComments().comments());
        }

    }

}