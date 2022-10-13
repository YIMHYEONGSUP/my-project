package hyeong.backend.domain.market.service;

import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.controller.MarketControlResponseDTO;
import hyeong.backend.domain.market.dto.*;
import hyeong.backend.domain.market.dto.item.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketJoinResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTOV2;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import hyeong.backend.global.common.TokenDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {
    MarketJoinResponseDTO create(Market market);

    MarketResponseDTO findByEmail(MarketEmail marketEmail);

    MarketResponseDTOV2 findByEmailV2(MarketEmail marketEmail);

    TokenDTO update(final Market market, final MarketEmail marketEmail);

    void delete(final MarketEmail marketEmail);

    Page<MarketItemListResponseDTO> marketItemList(final MarketEmail marketEmail, final Pageable pageable);

    Page<MarketListResponseDTO> marketList(final LocationCondition locationCondition , final Pageable pageable);

     MarketControlResponseDTO marketStatus(MarketEmail marketEmail, MarketStatus marketStatus, List<Item> preparedItemList);

}
