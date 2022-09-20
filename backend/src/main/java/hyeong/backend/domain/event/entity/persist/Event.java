package hyeong.backend.domain.event.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.event.entity.vo.EventContents;
import hyeong.backend.domain.event.entity.vo.EventSubject;
import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Event extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @Embedded
    @Column(name = "event_subject")
    private EventSubject subject;

    @Embedded
    @Column(name = "event_contents")
    private EventContents contents;

    @Builder
    public Event(
            Market market,
            EventSubject subject,
            EventContents contents
    ) {
        this.market = market;
        this.subject = subject;
        this.contents = contents;
    }

}
