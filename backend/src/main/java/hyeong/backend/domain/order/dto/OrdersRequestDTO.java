package hyeong.backend.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
@ApiModel
@JsonTypeName("orders")
@NoArgsConstructor
public class OrdersRequestDTO {

    private MarketEmail marketEmail;
    private MemberEmail memberEmail;

    private List<Item> itemList;

    @Builder
    public OrdersRequestDTO(final MarketEmail marketEmail, final MemberEmail memberEmail, final List<Item> itemList) {
        this.marketEmail = marketEmail;
        this.memberEmail = memberEmail;
        this.itemList = itemList;
    }


}
