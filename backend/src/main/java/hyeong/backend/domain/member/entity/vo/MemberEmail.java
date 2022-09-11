package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberEmail {

    @Email
    @NotNull(message = "이메일은 필수 입력사항 입니다.")
    private String email;

    public static MemberEmail from(final String email) {
        return new MemberEmail(email);
    }

    @JsonValue
    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEmail memberEmail = (MemberEmail) o;
        return Objects.equals(email(), memberEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email());
    }

}
