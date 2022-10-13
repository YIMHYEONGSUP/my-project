package hyeong.backend.domain.member.entity.persist;

import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.domain.order.entity.persist.Orders;
import hyeong.backend.global.common.BaseTimeEntity;
import hyeong.backend.global.common.vo.RoleType;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Embedded
    @Column(name = "member_email")
    private MemberEmail memberEmail;

    @Embedded
    @Column(name = "member_password")
    private MemberPassword memberPassword;

    @Embedded
    @Column(name = "member_name")
    private MemberName memberName;

    @Embedded
    @Column(name = "member_nickname")
    @JoinColumn
    private MemberNickName memberNickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", length = 20)
    private RoleType memberRoleType;

    @OneToMany(mappedBy = "member")
    private List<Orders> orderList;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews;

    @Builder
    public Member(
            MemberEmail memberEmail,
            MemberPassword memberPassword,
            MemberName memberName,
            MemberNickName memberNickName,
            RoleType memberRoleType,
            List<Review> reviews
    ) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberRoleType = memberRoleType;
        this.reviews = reviews;
    }

    public MemberJoinRequestDTO toJoinRequestDTO() {
        return MemberJoinRequestDTO.builder()
                .email(memberEmail)
                .password(memberPassword)
                .name(memberName)
                .nickname(memberNickName)
                .build();
    }

    public Member update(final Member member, final PasswordEncoder encoder) {
        changeEmail(member.memberEmail);
        changePassword(member.memberPassword);
        changeNickName(member.memberNickName);
        encode(encoder);
        return this;
    }

    private void changeEmail(MemberEmail email) {
        this.memberEmail = email;
    }

    private void changePassword(MemberPassword password) {
        this.memberPassword = password;
    }

    private void changeNickName(MemberNickName nickname) {
        this.memberNickName = nickname;
    }

    public Member encode(final PasswordEncoder encoder) {
        memberPassword = MemberPassword.encode(memberPassword.memberPassword(), encoder);
        return this;
    }
}
