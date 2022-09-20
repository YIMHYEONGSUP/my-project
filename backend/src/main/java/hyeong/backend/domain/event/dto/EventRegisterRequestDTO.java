package hyeong.backend.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.event.entity.vo.EventContents;
import hyeong.backend.domain.event.entity.vo.EventSubject;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@JsonTypeName("event")
@NoArgsConstructor
public class EventRegisterRequestDTO {

    @Valid
    @JsonProperty("market")
    private Market market;

    @JsonProperty("subject")
    private EventSubject subject;

    @JsonProperty("contents")
    private EventContents contents;

    @Builder
    public EventRegisterRequestDTO(Market market, EventSubject subject, EventContents contents) {
        this.market = market;
        this.subject = subject;
        this.contents = contents;
    }

    public static EventRegisterRequestDTO from(final Event event) {
        return new EventRegisterRequestDTO(event.getMarket() , event.getSubject() , event.getContents());
    }

    public Event toEntity() {
        return Event.builder()
                .market(market)
                .subject(subject)
                .contents(contents)
                .build();
    }

}
