package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.entity.persist.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberEmail implements Serializable {

    @Email
    @NotNull(message = "이메일은 필수 입력사항 입니다.")
    private String memberEmail;

    public static MemberEmail from(final String memberEmail) {
        return new MemberEmail(memberEmail);
    }

    @JsonValue
    public String memberEmail() {
        return memberEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEmail memberEmail = (MemberEmail) o;
        return Objects.equals(memberEmail(), memberEmail.memberEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberEmail());
    }

    // test
    public Member toEntity() {
        return Member.builder()
                .memberEmail(from(memberEmail))
                .build();
    }

}
