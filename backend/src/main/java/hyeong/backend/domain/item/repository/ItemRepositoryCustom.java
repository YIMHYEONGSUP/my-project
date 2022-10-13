package hyeong.backend.domain.item.repository;

import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.ItemSearchCondition;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryCustom {

    Page<ItemResponseDTO> itemList (ItemSearchCondition condition);


    Page<ItemResponseDTO> itemListSimple (ItemSearchCondition condition , Pageable pageable);
    Page<ItemResponseDTO> itemListComplex (ItemSearchCondition condition , Pageable pageable);


    List<ItemResponseDTO> marketItemList(MarketEmail marketEmail);

}
