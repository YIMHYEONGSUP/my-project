package hyeong.backend.global.given;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.global.common.vo.LocationAddress;

public class GivenMarket {

    public static Market createMarket() {

        return Market.builder()
                .email(MarketEmail.from("market@gmail.com"))
                .password(MarketPassword.from("1234"))
                .status(MarketStatus.PREPARED)
                .name(MarketName.from("marketName"))
                .locationAddress(LocationAddress.from("부천","중동","집주소","12345"))
                .reviews(null)
                .events(null)
                .build();
    }



}
