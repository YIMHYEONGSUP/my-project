package hyeong.backend.domain.item.controller;

import hyeong.backend.domain.item.dto.ItemRegisterRequestDTO;
import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("아이템 관리 API")
@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemRegisterResponseDTO> create(
            @Valid @RequestBody ItemRegisterRequestDTO requestDTO
            ) {

        log.info("requestDTO = {}" , requestDTO );

        Item item = requestDTO.toEntity();

        URI createdItemURI = URI.create(String.format("/api/v1/items/%d", item.getId()));
        return ResponseEntity.created(createdItemURI).body(itemService.create(item));

    }




}
