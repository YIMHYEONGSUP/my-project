package hyeong.backend.global.given;

import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.global.common.vo.RoleType;

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
                .memberEmail(USER_EMAIL)
                .memberPassword(USER_PASSWORD)
                .memberName(USER_NAME)
                .memberRoleType(RoleType.USER)
                .memberNickName(USER_NICK_NAME)
                .build();
    }

    public static Member of(final MemberEmail email, final MemberPassword password, final MemberName name,
                            final MemberNickName nickname) {

        return Member.builder()
                .memberEmail(email)
                .memberPassword(password)
                .memberName(name)
                .memberRoleType(RoleType.USER)
                .memberNickName(nickname)
                .build();
    }
}