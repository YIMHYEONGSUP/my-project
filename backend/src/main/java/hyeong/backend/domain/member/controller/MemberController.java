package hyeong.backend.domain.member.controller;

import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponseDTO> join(@Valid @RequestBody MemberJoinRequestDTO requestDTO) {
        Member member = requestDTO.toEntity();

        URI createdURI = URI.create(String.format("/api/v1/join", member.getId()));
        return ResponseEntity.created(createdURI).body(memberService.save(member));
    }
}
