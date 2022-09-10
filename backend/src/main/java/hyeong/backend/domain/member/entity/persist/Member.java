package hyeong.backend.domain.member.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberName;
import hyeong.backend.domain.member.entity.vo.MemberNickName;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

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
    private MemberNickName nickname;

    @Builder
    public Member(
            MemberEmail email,
            MemberPassword password,
            MemberName name,
            MemberNickName nickname
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }


}
