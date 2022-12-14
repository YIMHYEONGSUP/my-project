package hyeong.backend.domain.item.service;

import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.ItemUpdateResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.repository.ItemRepository;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public ItemRegisterResponseDTO create(Item item) {
        log.info("item market = {} , {}" , item.getMarket().getMarketEmail().email() , item.getItemName().name());

        return ItemRegisterResponseDTO.from(itemRepository.save(item));
    }

    @Override
    public ItemUpdateResponseDTO update(Item item) {
        return null;
    }


}
