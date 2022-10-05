package hyeong.backend.domain.item.service;

import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.dto.ItemResponseDTO;
import hyeong.backend.domain.item.dto.ItemUpdateResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    ItemRegisterResponseDTO create(Item item);

    ItemUpdateResponseDTO update(Item item);


    PageImpl<ItemResponseDTO> itemList();
}
