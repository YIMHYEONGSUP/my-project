package hyeong.backend.domain.member.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Api("회원 관리 API")
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberManagementController {

    private final MemberManagementService memberManagementService;

    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력 받아 저장")
    @ApiParam(name = "회원 가입 데이터 전달 DTO")
    public ResponseEntity<JoinResponseDTO> create(@Valid, @RequestBody JoinRequestDTO request) {
        Member member = request.toEntity();

        URI createMemberURI = URI.create(String.format("/api/v1/member/%d" , member.getId()))
    }
}
