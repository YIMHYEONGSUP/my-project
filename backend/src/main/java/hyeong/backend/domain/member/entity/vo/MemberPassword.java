package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPassword {

    private String password;

    public static MemberPassword from(final String password) {
        return new MemberPassword(password);
    }

    @JsonValue
    public String memberPassword (){
        return password;
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
        return Objects.equals(memberPassword(), memberPassword.password);
    }

}
