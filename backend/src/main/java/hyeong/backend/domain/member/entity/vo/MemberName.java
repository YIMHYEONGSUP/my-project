package hyeong.backend.domain.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberName {

    @NotNull(message = "이름은 필수 입력사항 입니다.")
    private String memberName;

    public static MemberName from(final String memberName) {
        return new MemberName(memberName);
    }

    @JsonValue
    public String memberName() {
        return memberName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)return true;
        if(o == null || getClass() != o.getClass()) return false;

        MemberName memberName = (MemberName) o;
        return Objects.equals(memberName(), memberName.memberName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(memberName());
    }
}
