package hyeong.backend.domain.market.entity.persist;

import hyeong.backend.domain.event.entity.persist.Event;
import hyeong.backend.domain.item.entity.persist.Item;
import hyeong.backend.domain.market.entity.vo.*;
import hyeong.backend.global.common.vo.RoleType;
import hyeong.backend.global.common.vo.LocationAddress;
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

    @Id
    @Embedded
    @Column(name = "market_email" , unique = true , nullable = false )
    private MarketEmail email;

    @Embedded
    @Column(name = "market_password")
    private MarketPassword password;

    @Embedded
    @Column(name = "market_name")
    private MarketName name;

    @Embedded
    @Column(name = "market_location_address")
    private LocationAddress locationAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_role_type", length = 20)
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_status")
    private MarketStatus status;

    @OneToMany(mappedBy = "market" , cascade = CascadeType.ALL)
    @Column(name = "market_item")
    private List<Item> items;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_reviews")
    private List<Review> reviews;

    @OneToMany(mappedBy = "market")
    @Column(name = "market_events")
    private List<Event> events;


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
            final RoleType roleType, final MarketStatus status,
           final MarketName name,
            final MarketEmail email, final MarketPassword password,
            final LocationAddress locationAddress, final List<Review> reviews,
            final List<Event> events
    ) {
        this.roleType = roleType;
        this.status = status;
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationAddress = locationAddress;
        this.reviews = reviews;
        this.events = events;
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

    public void prepared() {
        this.status = MarketStatus.PREPARED;
    }


    public Market encode(final PasswordEncoder encoder) {
        password = MarketPassword.encode(password.password(), encoder);
        return this;
    }

}
