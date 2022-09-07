package hyeong.backend.Service;

import hyeong.backend.Dto.Member.MemberDto;
import hyeong.backend.Entity.Member;
import hyeong.backend.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void save(String username, int age) {
        Member member = Member.builder()
                .username(username)
                .age(age)
                .build();
        memberRepository.save(member);
    }

    @Override
    public Optional<MemberDto> find(Long id) {

        Optional<Member> findMember = memberRepository.findById(id);
        MemberDto memberDto = new MemberDto(
                findMember.get().getId(),
                findMember.get().getUsername(),
                findMember.get().getAge()
        );

        return Optional.ofNullable(memberDto);

    }

    @Override
    public Page<MemberDto> findAll(Pageable pageable) {
        return memberRepository.searchMemberAll(pageable);
    }
}
