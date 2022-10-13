package hyeong.backend.domain.item.service;

import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.ItemUpdateResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    ItemRegisterResponseDTO create(Item item);

    ItemUpdateResponseDTO update(Item item);


}
