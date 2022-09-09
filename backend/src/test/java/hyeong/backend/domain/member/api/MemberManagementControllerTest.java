package hyeong.backend.domain.member.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyeong.backend.domain.auth.application.MemberAuthService;
import hyeong.backend.domain.auth.details.MemberDetailServiceCustom;
import hyeong.backend.domain.member.application.MemberManagementService;
import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.persist.MemberQueryRepository;
import hyeong.backend.domain.member.domain.persist.MemberRepository;
import hyeong.backend.domain.member.domain.persist.RoleType;
import hyeong.backend.domain.member.domain.vo.*;
import hyeong.backend.domain.member.dto.JoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.error_exception.MemberNotFoundException;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
@Slf4j
class MemberManagementControllerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberManagementService memberManagementService;

    @Autowired
    private MemberAuthService memberAuthService;

    @Autowired
    private MemberDetailServiceCustom memberDetailServiceCustom;


    @Test
    public void test() {


        UserEmail email = UserEmail.from("gud1313@gmail.com");
        UserPassword password = UserPassword.from("1234");
        UserName name = UserName.from("임형섭");
        UserNickName nickname = UserNickName.from("별명");
        UserProfileImage image = UserProfileImage.from("");
        LocalDate birth = LocalDate.of(2022, 12, 24);
        RoleType user = RoleType.USER;

        Member member = new Member(email,password,name, user ,nickname,birth , image);
        em.persist(member);
        em.flush();
        em.clear();

        MemberResponseDTO one = memberManagementService.findOne(UserEmail.from("gud1313@gmail.com"));
        System.out.println("one = " + one.getEmail().getEmail());

        TokenDTO token = memberAuthService.authorize(UserEmail.from("gud1313@gmail.com"), UserPassword.from("1234"));
        System.out.println("token = " + token.toString());

        System.out.println("회원 못찾음 ");

    }

}