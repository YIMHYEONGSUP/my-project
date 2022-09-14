package hyeong.backend.domain.market.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.market.entity.vo.LocationAddress;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private long id;

    @Embedded
    private MarketName marketName;

    @Embedded
    private MarketEmail marketEmail;

    @Embedded
    private LocationAddress locationAddress;

    @OneToMany(mappedBy = "market")
    private List<Review> reviews;

    @Builder
    public Market(final MarketName marketName, MarketEmail marketEmail, LocationAddress locationAddress, List<Review> reviews) {
        this.marketName = marketName;
        this.marketEmail = marketEmail;
        this.locationAddress = locationAddress;
        this.reviews = reviews;
    }

}
