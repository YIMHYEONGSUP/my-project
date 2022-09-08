package hyeong.backend.domain.member.api;


import hyeong.backend.domain.member.application.MemberManagementService;
import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.vo.UserEmail;
import hyeong.backend.domain.member.dto.JoinRequestDTO;
import hyeong.backend.domain.member.dto.JoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.dto.MemberUpdateDTO;
import hyeong.backend.global.common.TokenDTO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Api("회원 관리 API")
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberManagementController {

    private final MemberManagementService memberManagementService;

    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력 받아 저장")
    @ApiParam(name = "회원 가입 데이터 전달 DTO")
    public ResponseEntity<JoinResponseDTO> create(@Valid @RequestBody JoinRequestDTO request) {
        Member member = request.toEntity();

        URI createMemberURI = URI.create(String.format("/api/v1/member/%d" , member.getId()));
        return ResponseEntity.created(createMemberURI).body(memberManagementService.create(member));
    }

    @PatchMapping
    @ApiOperation(value = "회원 수정" , notes = "회원 수정 정보를 입력 받아 변경한다")
    public ResponseEntity<TokenDTO> update(
            @ApiParam(name = "변경 된 회원 데이터")
            @Valid
            @RequestBody MemberUpdateDTO updateDTO
            ){
        Member member = updateDTO.toEntity();
        return ResponseEntity.ok(memberManagementService.update(member, UserEmail.from(getEmail())));
    }

    @GetMapping("/findByEmail")
    @ApiOperation(value = "회원 조회", notes = "회원 정보를 보여주는 api")
    public ResponseEntity<MemberResponseDTO> findByEmail() {
        return ResponseEntity.ok(memberManagementService.findOne(UserEmail.from(getEmail())));
    }

    @DeleteMapping
    @ApiOperation(value = "회원 삭제", notes = "회원 정보를 삭제한다")
    public ResponseEntity<Void> delete() {
        memberManagementService.delete(UserEmail.from(getEmail()));
        return ResponseEntity.noContent().build();
    }


    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
