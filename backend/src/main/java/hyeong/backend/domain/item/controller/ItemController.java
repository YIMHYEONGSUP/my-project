package hyeong.backend.domain.item.controller;

import hyeong.backend.domain.item.dto.ItemRegisterRequestDTO;
import hyeong.backend.domain.item.dto.ItemRegisterResponseDTO;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.item.service.ItemService;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.service.MarketService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("아이템 관리 API")
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final MarketService marketService;

    @PostMapping
    public ResponseEntity<ItemRegisterResponseDTO> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ItemRegisterRequestDTO requestDTO
            ) {

        log.info("user info = {}" , user.getUsername());

        Item item = requestDTO.toEntity();
        item.setMarket(marketService.findByEmailV2(MarketEmail.from(user.getUsername())).toEntity());

        URI createdItemURI = URI.create(String.format("/item/%d", item.getId()));
        return ResponseEntity.created(createdItemURI).body(itemService.create(item));

    }

    @GetMapping("/{email}")
    public ResponseEntity<?> itemList(
            @AuthenticationPrincipal User user
    ) {

        ResponseEntity<?> result = null;

        try{
            result = new ResponseEntity<>(itemService.itemList() , HttpStatus.OK);
        }catch (Exception e){

        }

        return result;
    }



}
