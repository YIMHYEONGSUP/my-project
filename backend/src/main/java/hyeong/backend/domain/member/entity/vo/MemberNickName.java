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

    private String memberNickname;

    public static MemberNickName from(String memberNickname) {
        return new MemberNickName(memberNickname);
    }

    @JsonValue
    public String memberNickname() {
        return memberNickname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNickname());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this != o || getClass() != o.getClass())return false;
        MemberNickName memberNickName = (MemberNickName) o;
        return Objects.equals(memberNickname(), memberNickName.memberNickname);
    }
}
