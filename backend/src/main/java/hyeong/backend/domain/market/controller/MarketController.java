package hyeong.backend.domain.market.controller;

import hyeong.backend.domain.market.dto.*;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.service.MarketService;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.dto.MemberUpdateDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.global.common.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("마켓 관리 Api")
@RestController
@RequestMapping("/api/v1/market")
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

        URI createMarketURI = URI.create(String.format("/api/v1/markets/%d", market.getId()));
        return ResponseEntity.created(createMarketURI).body(marketService.create(market));
    }

    @PatchMapping
    @ApiOperation(value = "마켓 회원 수정", notes = "회원 수정 정보를 입력 받아 변경한다.")
    public ResponseEntity<TokenDTO> update(
            @ApiParam(name = "변경된 회원 데이터")
            @Valid @RequestBody MarketUpdateDTO updateDTO) {
        Market market = updateDTO.toEntity();

        return ResponseEntity.ok(marketService.update(market, MarketEmail.from(market.getEmail().email())));
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


}
