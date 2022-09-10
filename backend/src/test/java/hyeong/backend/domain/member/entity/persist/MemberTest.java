package hyeong.backend.domain.member.entity.persist;

import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void create() {

        Member newMember = Member.builder()
                .email(MemberEmail.from("gud1313@naver.com"))
                .password(MemberPassword.from("1234"))
                .name(MemberName.from("임형섭"))
                .nickname(MemberNickName.from("별명"))
                .build();

        MemberEmail email = newMember.getEmail();
        System.out.println("email = " + email.memberEmail());

        memberService.save(newMember);
        MemberResponseDTO foundMember = memberService.findByEmail(MemberEmail.from("gud1313@naver.com"));
        System.out.println(foundMember.getEmail().memberEmail());


    }

}