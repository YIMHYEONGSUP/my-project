package hyeong.backend.domain.item.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.member.entity.vo.MemberName;
import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemName {

    @NotNull(message = "이름은 필수 입력입니다.")
    private String name;

    @JsonValue
    public String name() {
        return name;
    }

    public static ItemName from(final String name) {
        return new ItemName(name);
    }

}
