package hyeong.backend.domain.market.entity.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.dto.MarketJoinRequestDTO;
import hyeong.backend.domain.market.entity.vo.*;
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
    @Column(name = "market_name")
    private MarketName name;

    @Embedded
    @Column(name = "market_email")
    private MarketEmail email;

    @Embedded
    @Column(name = "market_password")
    private MarketPassword password;
    @Embedded
    @Column(name = "market_address")
    private TemporarilyAddress locationAddress;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_item")
    private List<Item> items;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_reviews")
    private List<Review> reviews;

    @Embedded
    @Column(name = "market_event_list")
    private EventList eventList;


/*  테이블
    @Embedded
    @Column(name = "market_inside_customer_info")
    private InsideCustomerInfo insideCustomerInfo;

    @Embedded
    @Column(name = "market_outside_customer_info")
    private OutSideCustomerInfo outSideCustomerInfo;
*/

    @Builder
    public Market(
            final MarketName name,final MarketEmail email,
            final MarketPassword password,
            final TemporarilyAddress locationAddress, final List<Review> reviews,
            final EventList eventList
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
        this.reviews = reviews;
        this.eventList = eventList;
    }


    public MarketJoinRequestDTO toJoinRequestDTO() {
        return MarketJoinRequestDTO.builder()
                .email(email)
                .password(password)
                .name(name)
                .locationAddress(locationAddress)
                .build();
    }

    public Market update(final Market market, final PasswordEncoder encoder) {
        changeEmail(market.email);
        changePassword(market.password);
        changeNickName(market.name);
        encode(encoder);
        return this;
    }

    private void init(){

    }

    private void changeEmail(MarketEmail email) {
        this.email = email;
    }

    private void changePassword(MarketPassword password) {
        this.password = password;
    }

    private void changeNickName(MarketName name) {
        this.name = name;
    }


    public Market encode(final PasswordEncoder encoder) {
        password = MarketPassword.encode(password.password(), encoder);
        return this;
    }

}
