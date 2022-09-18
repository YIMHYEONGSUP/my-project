package hyeong.backend.domain.item.service;

import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.dto.ItemUpdateResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    ItemRegisterResponseDTO create(Item item);

    ItemUpdateResponseDTO update(Item item);


}
