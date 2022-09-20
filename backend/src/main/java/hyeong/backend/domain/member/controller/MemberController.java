package hyeong.backend.domain.member.controller;

import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.dto.MemberUpdateDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.service.MemberService;
import hyeong.backend.global.common.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Api("회원 관리 Api")
@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    @ApiParam(name = "회원 가입 데이터 전달 DTO")
    @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력받아 저장한다.")
    public ResponseEntity<MemberJoinResponseDTO> join(@Valid @RequestBody MemberJoinRequestDTO requestDTO) {

        log.info("controller request = {}" , requestDTO.getEmail());
        Member member = requestDTO.toEntity();
        log.info("controller member = {}" , member.getEmail());

        URI createdMemberURI = URI.create(String.format("/api/v1/member/%d", member.getId()));
        return ResponseEntity.created(createdMemberURI).body(memberService.create(member));
    }

    @PatchMapping
    @ApiOperation(value = "회원 수정", notes = "회원 수정 정보를 입력 받아 변경한다.")
    public ResponseEntity<TokenDTO> update(
            @ApiParam(name = "변경된 회원 데이터")
            @Valid @RequestBody MemberUpdateDTO updateDTO) {
        Member member = updateDTO.toEntity();

        return ResponseEntity.ok(memberService.update(member, MemberEmail.from(member.getEmail().email())));
    }

    @DeleteMapping
    @ApiOperation(value = "회원 삭제", notes = "회원 정보를 삭제한다.")
    public ResponseEntity<Void> delete() {
        memberService.delete(MemberEmail.from(getEmail()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByEmail")
    @ApiOperation(value = "회원 조회", notes = "회원 정보를 보여주는 API")
    public ResponseEntity<MemberResponseDTO> findByEmail() {
        return ResponseEntity.ok(memberService.findByEmail(MemberEmail.from(getEmail())));
    }

    private String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }



}
