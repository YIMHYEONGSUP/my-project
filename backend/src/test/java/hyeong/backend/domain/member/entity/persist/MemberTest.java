package hyeong.backend.domain.member.entity.persist;

import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

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

    }

}