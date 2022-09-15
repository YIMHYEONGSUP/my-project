package hyeong.backend.domain.market.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Market {

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "market_id")
    private Long id;

    @Embedded
    private MarketName marketName;

    @Embedded
    private MarketEmail marketEmail;

    @Embedded
    private MarketPassword marketPassword;
    @Embedded
    private LocationAddress locationAddress;

    @OneToMany(mappedBy = "market")
    private List<Review> reviews;

    @Embedded
    private EventList eventList;

    @Embedded
    private InsideCustomerInfo insideCustomerInfo;

    @Embedded
    private OutSideCustomerInfo outSideCustomerInfo;

    @Builder
    public Market(
            final MarketName marketName,final MarketEmail marketEmail,
            final MarketPassword marketPassword,
            final LocationAddress locationAddress,final List<Review> reviews,
            final EventList eventList , final InsideCustomerInfo insideCustomerInfo,
            final OutSideCustomerInfo outSideCustomerInfo
    ) {
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.marketPassword = marketPassword;
        this.locationAddress = locationAddress;
        this.reviews = reviews;
        this.eventList = eventList;
        this.insideCustomerInfo = insideCustomerInfo;
        this.outSideCustomerInfo = outSideCustomerInfo;
    }

    public Market encode(final PasswordEncoder encoder) {
        marketPassword = MarketPassword.encode(marketPassword.password(), encoder);
        return this;
    }

}
