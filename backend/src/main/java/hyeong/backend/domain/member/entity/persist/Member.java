package hyeong.backend.domain.member.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.market.entity.persist.Review;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @JsonIgnore
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @Column(name = "member_email")
    private MemberEmail email;

    @Embedded
    @Column(name = "member_password")
    private MemberPassword password;

    @Embedded
    @Column(name = "member_name")
    private MemberName name;

    @Embedded
    @Column(name = "member_nickname")
    @JoinColumn
    private MemberNickName nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", length = 20)
    private RoleType roleType;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews;

    @Builder
    public Member(
            MemberEmail email,
            MemberPassword password,
            MemberName name,
            MemberNickName nickname,
            RoleType roleType,
            List<Review> reviews
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.roleType = roleType;
        this.reviews = reviews;
    }

    public MemberJoinRequestDTO toJoinRequestDTO() {
        return MemberJoinRequestDTO.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }

    public Member update(final Member member, final PasswordEncoder encoder) {
        changeEmail(member.email);
        changePassword(member.password);
        changeNickName(member.nickname);
        encode(encoder);
        return this;
    }

    private void changeEmail(MemberEmail email) {
        this.email = email;
    }

    private void changePassword(MemberPassword password) {
        this.password = password;
    }

    private void changeNickName(MemberNickName nickname) {
        this.nickname = nickname;
    }

    public Member encode(final PasswordEncoder encoder) {
        password = MemberPassword.encode(password.password(), encoder);
        return this;
    }
}
