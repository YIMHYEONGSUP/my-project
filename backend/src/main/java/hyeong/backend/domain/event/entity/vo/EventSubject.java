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
public class EventSubject {

    @NotNull(message = "이벤트 제목은 필수 입니다.")
    private String subject;

    @JsonValue
    public String subject () {
        return subject;
    }

    public static EventSubject from(final String subject) {
        return new EventSubject(subject);
    }





}
