package hyeong.backend.domain.market.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.global.common.vo.LocationAddress;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MarketListResponseDTO {

    private MarketName marketName;

    private MarketStatus marketStatus;

    private LocationAddress locationAddress;

    @QueryProjection
    public MarketListResponseDTO(final MarketName marketName , final MarketStatus marketStatus ,
                                 final LocationAddress locationAddress) {
        this.marketName = marketName;
        this.marketStatus = marketStatus;
        this.locationAddress = locationAddress;
    }

}
