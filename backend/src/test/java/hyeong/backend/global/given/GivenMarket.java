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
                .marketEmail(MarketEmail.from("market@gmail.com"))
                .marketPassword(MarketPassword.from("1234"))
                .marketStatus(MarketStatus.PREPARED)
                .marketName(MarketName.from("marketName"))
                .marketLocationAddress(LocationAddress.from("부천","중동","집주소","12345"))
                .reviews(null)
                .events(null)
                .build();
    }

    public static Market createOrderedMarket(int index) {

        return Market.builder()
                .marketEmail(MarketEmail.from("market"+index+"@gmail.com"))
                .marketPassword(MarketPassword.from("1234"))
                .marketStatus(MarketStatus.PREPARED)
                .marketName(MarketName.from("marketName"+index))
                .marketLocationAddress(LocationAddress.from("부천","중동","집주소","12345"))
                .reviews(null)
                .events(null)
                .build();
    }


}
