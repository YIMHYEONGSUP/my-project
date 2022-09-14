package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import lombok.RequiredArgsConstructor;
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

        ((List<Review>) new ArrayList<Review>()).add(newReview);

        Market market = Market.builder()
                .marketName(MarketName.from("가게이름"))
                .marketEmail(MarketEmail.from("가게이메일@aa.com"))
                .locationAddress(LocationAddress.from("부천", "중동", "201호", "12345"))
                .reviews(new ArrayList<Review>())
                .build();

        List<Review> reviews = market.getReviews();
        for (Review review : reviews) {
            System.out.println("review = " + review.getComments());
        }

    }

}