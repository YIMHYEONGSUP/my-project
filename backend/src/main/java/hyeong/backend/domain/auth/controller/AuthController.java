package hyeong.backend.domain.auth.controller;

import hyeong.backend.domain.auth.dto.LoginRequestDTO;
import hyeong.backend.domain.auth.service.MemberAuthService;
import hyeong.backend.global.common.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO , HttpServletResponse response) {

        log.info("auth controller requestDto = {}", requestDTO);
        log.info("auth controller requestDto email = {} , password = {}" , requestDTO.getEmail() , requestDTO.getPassword());

        return new ResponseEntity<>(
                memberAuthService.authorize(requestDTO.getEmail(), requestDTO.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@Valid @RequestBody TokenDTO requestToken) {
        return ResponseEntity.ok(memberAuthService.reissue(requestToken.getAccessToken(), requestToken.getRefreshToken()));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody TokenDTO tokenDTO) {
        memberAuthService.logout(tokenDTO.getRefreshToken() , tokenDTO.getAccessToken());
        return ResponseEntity.ok().build();
    }


}
