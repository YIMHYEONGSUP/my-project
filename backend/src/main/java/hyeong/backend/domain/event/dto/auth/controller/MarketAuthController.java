package hyeong.backend.domain.event.dto.auth.controller;

import hyeong.backend.domain.event.dto.auth.dto.MarketLoginRequestDTO;
import hyeong.backend.domain.event.dto.auth.service.MarketAuthService;
import hyeong.backend.global.common.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/market")
@RequiredArgsConstructor
@Slf4j
public class MarketAuthController {

    private final MarketAuthService marketAuthService;

    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody MarketLoginRequestDTO requestDTO , HttpServletResponse response) {

        log.info("auth controller requestDto = {}", requestDTO);
        log.info("auth controller requestDto email = {} , password = {}" , requestDTO.getEmail() , requestDTO.getPassword());

        return new ResponseEntity<>(
                marketAuthService.login(requestDTO.getEmail(), requestDTO.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@Valid @RequestBody TokenDTO requestToken ) {
        return ResponseEntity.ok(marketAuthService.reissue(requestToken.getAccessToken(), requestToken.getRefreshToken()));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody TokenDTO tokenDTO) {
        marketAuthService.logout(tokenDTO.getRefreshToken() , tokenDTO.getAccessToken());
        return ResponseEntity.ok().build();
    }


}
