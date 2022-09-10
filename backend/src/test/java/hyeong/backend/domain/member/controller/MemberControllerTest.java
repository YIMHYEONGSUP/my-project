package hyeong.backend.domain.member.controller;

import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @Test
    void findByEmailTest() throws Exception {

        Member build = Member.builder()
                .email(MemberEmail.from("1234@gmail.com"))
                .password(MemberPassword.from("1234"))
                .name(MemberName.from("member1"))
                .nickname(MemberNickName.from("nickMember1"))
                .build();

    }
}