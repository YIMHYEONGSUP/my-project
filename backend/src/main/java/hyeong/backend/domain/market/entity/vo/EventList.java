package hyeong.backend.domain.market.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EventList {

    private String eventName;

    private LocalDate beginEventInfo;
    private LocalDate endEventInfo;

    private String eventContent;

    public static EventList init() {
        return new EventList(null , null , null , null);
    }

    public static EventList from (final String eventName , final LocalDate beginEventInfo , final LocalDate endEventInfo , String eventContent){
        return new EventList(eventName, beginEventInfo, endEventInfo, eventContent);
    }

    @JsonValue
    public String eventName() {
        return eventName;
    }

    @JsonValue
    public LocalDate beginEventInfo() {
        return beginEventInfo;
    }

    @JsonValue
    public LocalDate endEventInfo() {
        return endEventInfo;
    }

    @JsonValue
    public String eventContent() {
        return eventContent;
    }

}
