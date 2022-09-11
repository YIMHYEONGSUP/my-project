package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberNickName {

    private String nickname;

    public static MemberNickName from(String nickname) {
        return new MemberNickName(nickname);
    }

    @JsonValue
    public String nickname() {
        return nickname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this != o || getClass() != o.getClass())return false;
        MemberNickName memberNickName = (MemberNickName) o;
        return Objects.equals(nickname(), memberNickName.nickname);
    }
}
