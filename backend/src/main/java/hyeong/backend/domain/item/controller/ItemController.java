package hyeong.backend.domain.item.controller;

import hyeong.backend.domain.item.dto.ItemRegisterRequestDTO;
import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.domain.market.dto.MarketResponseDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.service.MarketService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("아이템 관리 API")
@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final MarketService marketService;

    @PostMapping
    public ResponseEntity<ItemRegisterResponseDTO> create(
            @Valid @RequestBody ItemRegisterRequestDTO requestDTO
            ) {

        log.info("requestDTO = {}" , requestDTO );

        Item item = requestDTO.toEntity();

        MarketResponseDTO byEmail = marketService.findByEmail(requestDTO.getMarketEmail());
        Market market = byEmail.toEntity();

        item.setMarket(marketService.findByEmail(requestDTO.getMarketEmail()).toEntity());

        log.info("item inner market = {}" , item.getMarket().getName().name());

        URI createdItemURI = URI.create(String.format("/api/v1/item/%d", item.getId()));
        return ResponseEntity.created(createdItemURI).body(itemService.create(item));

    }




}
