package hyeong.backend.domain.market.entity.persist;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import hyeong.backend.domain.market.entity.vo.Rating;
import hyeong.backend.domain.member.entity.persist.Member;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Review {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    @JoinColumn(name = "review")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String comments;

    @Builder
    public Review(
            Market market,
            Member member,
            Rating rating,
            String comments
    ) {
        this.market = market;
        this.member = member;
        this.rating = rating;
        this.comments = comments;
    }




}
