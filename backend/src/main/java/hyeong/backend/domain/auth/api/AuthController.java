package hyeong.backend.domain.auth.api;

import antlr.Token;
import hyeong.backend.domain.auth.application.MemberAuthService;
import hyeong.backend.domain.auth.dto.LoginRequestDto;
import hyeong.backend.global.common.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/vi/members")
@RequiredArgsConstructor
public class AuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return new ResponseEntity<>(memberAuthService.authorize(requestDto.getEmail(), requestDto.getPassword()), HttpStatus.OK);
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