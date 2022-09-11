package hyeong.backend.domain.member.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.entity.vo.*;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @JsonIgnore
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private MemberEmail email;

    @Embedded
    private MemberPassword password;

    @Embedded
    private MemberName name;

    @Embedded
    private MemberNickName nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", length = 20)
    private RoleType roleType;

    @Builder
    public Member(
            MemberEmail email,
            MemberPassword password,
            MemberName name,
            MemberNickName nickname,
            RoleType roleType
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.roleType = roleType;
    }

    public MemberJoinRequestDTO toJoinRequestDTO() {
        return MemberJoinRequestDTO.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }

    public Member encode(final PasswordEncoder encoder) {
        password = MemberPassword.encode(password.password(), encoder);
        return this;
    }
}
