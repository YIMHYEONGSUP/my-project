package hyeong.backend.domain.event.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EventContents {

    @NotNull(message = "이벤트 내용 입력은 필수 입니다.")
    private String contents;

    @JsonValue
    public String contents () {
        return contents;
    }

    public static EventContents from(final String contents) {
        return new EventContents(contents);
    }



}
