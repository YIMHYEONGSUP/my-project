package hyeong.backend.domain.market.controller;

import hyeong.backend.domain.market.dto.*;
import hyeong.backend.domain.market.dto.item.MarketItemListResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketJoinRequestDTOSerialize;
import hyeong.backend.domain.market.dto.marketUser.MarketJoinResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketResponseDTO;
import hyeong.backend.domain.market.dto.marketUser.MarketUpdateDTO;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.service.MarketService;
import hyeong.backend.global.common.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("마켓 관리 Api")
@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
@Slf4j
public class MarketController {

    private final MarketService marketService;

    @PostMapping
    @ApiParam(name = "마켓 회원 가입 데이터 전달 DTO")
    @ApiOperation(value = " 마켓 회원 가입", notes = " 마켓 회원 정보를 입력받아 저장한다.")
    public ResponseEntity<MarketJoinResponseDTO> join(
            @Valid@RequestBody MarketJoinRequestDTOSerialize requestDTO
    ) {

        Market market = requestDTO.toEntity();

//        URI createMarketURI = URI.create(String.format("/market/%d", market.getId()));
        URI createMarketURI = URI.create(String.format("/market/%s", market.getMarketEmail()));
        return ResponseEntity.created(createMarketURI).body(marketService.create(market));
    }

    @PatchMapping("/{email}")
    @ApiOperation(value = "마켓 회원 수정", notes = "회원 수정 정보를 입력 받아 변경한다.")
    public ResponseEntity<TokenDTO> update(
            @PathVariable String email,
            @ApiParam(name = "변경된 회원 데이터")
            @Valid @RequestBody MarketUpdateDTO updateDTO) {

        log.info("업데이트 실행 됨");

        Market market = updateDTO.toEntity();

        log.info("update market role type = {}" , market.getMarketRoleType());
        return ResponseEntity.ok(marketService.update(market, MarketEmail.from(email)));
    }

    @DeleteMapping
    @ApiOperation(value = "마켓 회원 삭제", notes = "마켓 회원 정보를 삭제한다.")
    public ResponseEntity<Void> delete() {
        marketService.delete(MarketEmail.from(getEmail()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByEmail")
    @ApiOperation(value = "마켓 회원 조회", notes = "마켓 회원 정보를 보여주는 API")
    public ResponseEntity<MarketResponseDTO> findByEmail() {
        return ResponseEntity.ok(marketService.findByEmail(MarketEmail.from(getEmail())));
    }

    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/itemList/{marketName}")
    @ApiOperation(value = "마켓 아이템 조회", notes = "마켓 아이템 리스트 조회 API")
    public ResponseEntity<Page<MarketItemListResponseDTO>> itemList(
            @RequestBody MarketSearchRequestDTO requestDTO
    ) {
        // temporarily page request
        return ResponseEntity.ok(marketService.marketItemList(requestDTO.getMarketEmail(), PageRequest.of(0, 10)));
    }

    @GetMapping("/marketList")
    @ApiOperation(value = "지역 내 마켓 조회" , notes = "지역 내 마켓 조회 API")
    public ResponseEntity<Page<MarketListResponseDTO>> marketList(
            @RequestBody LocationCondition locationCondition
    ) {
        // temporarily page request
        return ResponseEntity.ok(marketService.marketList(locationCondition, PageRequest.of(0, 10)));
    }

}
