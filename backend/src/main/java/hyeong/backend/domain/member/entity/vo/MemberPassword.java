package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.exceptions.PasswordMissMatchException;
import hyeong.backend.domain.member.exceptions.PasswordNullException;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPassword {

    @NotNull(message = "비밀번호는 필수 입력 사항입니다.")
    @Column(nullable = false , length = 100)
    private String memberPassword;

    public static MemberPassword encode(final String rawPassword, final PasswordEncoder passwordEncoder) {
        validateBlank(rawPassword);
        return new MemberPassword(passwordEncoder.encode(rawPassword));
    }

    private static void validateBlank(String rawPassword) {
        if (Objects.isNull(rawPassword) || rawPassword.isBlank()) {
            throw new PasswordNullException(ErrorCode.PASSWORD_NULL_ERROR);
        }
    }

    public void matches(final MemberPassword other, final PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(other.memberPassword(), this.memberPassword)) {
            throw new PasswordMissMatchException(ErrorCode.PASSWORD_MISS_MATCH);
        }
    }


    public static MemberPassword from(final String password) {
        return new MemberPassword(password);
    }

    @JsonValue
    public String memberPassword (){
        return memberPassword;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberPassword());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this != o || getClass() != o.getClass())return false;
        MemberPassword memberPassword = (MemberPassword) o;
        return Objects.equals(memberPassword(), memberPassword.memberPassword);
    }

}
