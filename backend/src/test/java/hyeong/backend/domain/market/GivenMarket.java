package hyeong.backend.domain.market;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;

public class GivenMarket {

    public static Market createMarket() {

        return Market.builder()
                .email(MarketEmail.from("market@gmail.com"))
                .password(MarketPassword.from("1234"))
                .name(MarketName.from("marketName"))
                .locationAddress(TemporarilyAddress.from("임시주소"))
                .reviews(null)
                .eventList(null)
                .insideCustomerInfo(InsideCustomerInfo.init())
                .outSideCustomerInfo(OutSideCustomerInfo.init())
                .build();
    }



}
