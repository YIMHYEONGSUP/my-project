package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class MarketTest {

    @Autowired
    EntityManager em;

    @Test
    public void market() {

        Member newMember = Member.builder()
                .email(MemberEmail.from("gud1313@naver.com"))
                .password(MemberPassword.from("1234"))
                .name(MemberName.from("임형섭"))
                .nickname(MemberNickName.from("별명"))
                .roleType(RoleType.USER)
                .build();


        Review review = Review.builder()
                .member(newMember)
                .rating(Rating.FIVE)
                .comments("아트릭스 스트롱 프로텍션 크림")
                .build();


        System.out.println("review = " + review.getMember().getEmail());
        System.out.println("review = " + review.getComments());

//        Market market = Market.builder()
//                .address(null)
//                .insideCustomerInfo(null)
//                .outSideCustomerInfo(null)
//                .eventList(null)
//                .review(review)
//                .build();
//
//        System.out.println("market = " + market);


    }

}