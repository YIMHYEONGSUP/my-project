package hyeong.backend.domain.auth.controller;

import hyeong.backend.domain.auth.dto.MemberLoginRequestDTO;
import hyeong.backend.domain.auth.service.MemberAuthService;
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
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody MemberLoginRequestDTO requestDTO , HttpServletResponse response) {

        log.info("auth controller requestDto = {}", requestDTO);
        log.info("auth controller requestDto email = {} , password = {}" , requestDTO.getEmail() , requestDTO.getPassword());

        return new ResponseEntity<>(
                memberAuthService.login(requestDTO.getEmail(), requestDTO.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@Valid @RequestBody TokenDTO requestToken ) {
        return ResponseEntity.ok(memberAuthService.reissue(requestToken.getAccessToken(), requestToken.getRefreshToken()));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody TokenDTO tokenDTO) {
        memberAuthService.logout(tokenDTO.getRefreshToken() , tokenDTO.getAccessToken());
        return ResponseEntity.ok().build();
    }


}
