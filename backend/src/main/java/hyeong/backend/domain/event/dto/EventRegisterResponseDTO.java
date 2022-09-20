package hyeong.backend.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.event.entity.vo.EventContents;
import hyeong.backend.domain.event.entity.vo.EventSubject;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonTypeName("event")
@AllArgsConstructor
public class EventRegisterResponseDTO {

    @JsonProperty("market")
    private Market market;

    @JsonProperty("subject")
    private EventSubject subject;

    @JsonProperty("contents")
    private EventContents contents;


    public static EventRegisterResponseDTO from(final Event event) {
        return new EventRegisterResponseDTO(event.getMarket() , event.getSubject() , event.getContents());
    }

}
