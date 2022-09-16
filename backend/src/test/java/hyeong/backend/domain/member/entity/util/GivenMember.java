package hyeong.backend.domain.member.entity.util;

import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;

import java.time.LocalDate;

public class GivenMember {

    public static final MemberEmail USER_EMAIL = MemberEmail.from("gud1313@naver.com");
    public static final MemberPassword USER_PASSWORD = MemberPassword.from("1234");
    public static final MemberName USER_NAME = MemberName.from("YIM");
    public static final RoleType ROLE_TYPE = RoleType.USER;
    public static final MemberNickName USER_NICK_NAME = MemberNickName.from("YIM1234");
    public static final LocalDate USER_BIRTH = LocalDate.of(1995, 11, 23);


    public static Member createMember() {

        return Member.builder()
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .name(USER_NAME)
                .roleType(RoleType.USER)
                .nickname(USER_NICK_NAME)
                .build();
    }

    public static Member of(final MemberEmail email, final MemberPassword password, final MemberName name,
                            final MemberNickName nickname) {

        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .roleType(RoleType.USER)
                .nickname(nickname)
                .build();
    }
}