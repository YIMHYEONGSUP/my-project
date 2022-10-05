package hyeong.backend.domain.market.repository;

import hyeong.backend.domain.market.dto.MarketItemListResponseDTO;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepositoryCustom {

    Page<MarketItemListResponseDTO> marketItemList(MarketEmail marketEmail , Pageable pageable);

}
