package hyeong.backend.domain.member.controller;

import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Api("회원 관리 Api")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/join")
    @ApiParam(name = "회원 가입 데이터 전달 DTO")
    @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력받아 저장한다.")
    public ResponseEntity<MemberJoinResponseDTO> join(@Valid @RequestBody MemberJoinRequestDTO requestDTO) {

        log.info("controller request = {}" , requestDTO.getEmail());
        Member member = requestDTO.toEntity();
        log.info("controller member = {}" , member.getEmail());

        URI createdURI = URI.create(String.format("/api/v1/join", member.getId()));
        return ResponseEntity.created(createdURI).body(memberService.save(member));
    }

    
}
